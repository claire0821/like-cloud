package com.mdd.seckill.service.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.vo.ProductDetaliSkuVo;
import com.mdd.common.vo.SeckillSessionWithSkusVo;
import com.mdd.seckill.config.SentinelUrlBlockHandler;
import com.mdd.seckill.feign.ICouponFeignService;
import com.mdd.seckill.feign.IProductFeignService;
import com.mdd.seckill.service.ISeckillService;
import com.mdd.seckill.to.SeckillSkuRedisTo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 16:16
 **/
@Slf4j
@Service
public class SeckillServiceImpl implements ISeckillService {

    @Autowired
    private ICouponFeignService iCouponFeignService;

    @Autowired
    private IProductFeignService iProductFeignService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String SESSION_CACHE_PREFIX = "seckill:sessions:";

    private final String SECKILL_CHARE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";    //+商品随机码

    @Override
    public void uploadSeckillSkuLatest3Days() {
        //1、扫描最近三天的商品需要参加秒杀的活动
        AjaxResult<List<SeckillSessionWithSkusVo>> lates3DaySession = iCouponFeignService.getLates3DaySession();
        if (lates3DaySession.getCode() == 0) {
            //上架商品
            List<SeckillSessionWithSkusVo> sessionData = lates3DaySession.getData();
            //缓存到Redis
            //1、缓存活动信息
            saveSessionInfos(sessionData);

            //2、缓存活动的关联商品信息
            saveSessionSkuInfo(sessionData);
        }
    }

    /**
     * 缓存秒杀活动信息
     * @param sessions
     */
    private void saveSessionInfos(List<SeckillSessionWithSkusVo> sessions) {
        sessions.stream().forEach(session -> {

            //获取当前活动的开始和结束时间的时间戳
            long startTime = session.getStartTime().getTime();
            long endTime = session.getEndTime().getTime();

            //存入到Redis中的key
            String key = SESSION_CACHE_PREFIX + startTime + "_" + endTime;

            //判断Redis中是否有该信息，如果没有才进行添加
            Boolean hasKey = RedisUtil.exists(key);
            //缓存活动信息
            if (!hasKey) {
                //获取到活动中所有商品的skuId
                List<Object> skuIds = session.getRelationSkus().stream()
                        .map(item -> item.getPromotionSessionId() + "-" + item.getSkuId().toString()).collect(Collectors.toList());
                RedisUtil.lSetLeft(key,skuIds);
            }
        });

    }

    /**
     * 缓存秒杀活动所关联的商品信息
     * @param sessions
     */
    private void saveSessionSkuInfo(List<SeckillSessionWithSkusVo> sessions) {

        sessions.stream().forEach(session -> {
            //准备hash操作，绑定hash
            BoundHashOperations<String, Object, Object> operations = RedisUtil.handler().boundHashOps(GlobalConfig.redisPrefix + SECKILL_CHARE_PREFIX);
            session.getRelationSkus().stream().forEach(seckillSkuVo -> {
                //生成随机码
                String token = UUID.randomUUID().toString().replace("-", "");
                String redisKey = seckillSkuVo.getPromotionSessionId().toString() + "-" + seckillSkuVo.getSkuId().toString();
                if (!operations.hasKey(redisKey)) {

                    //缓存我们商品信息
                    SeckillSkuRedisTo redisTo = new SeckillSkuRedisTo();
                    Long skuId = seckillSkuVo.getSkuId();
                    //1、先查询sku的基本信息，调用远程服务
                    final AjaxResult<ProductDetaliSkuVo> detial = iProductFeignService.getDetial(skuId);
                    if (detial.getCode() == 0) {
                        ProductDetaliSkuVo skuInfo = detial.getData();
                        redisTo.setSkuInfo(skuInfo);
                    }

                    //2、sku的秒杀信息
                    BeanUtils.copyProperties(seckillSkuVo,redisTo);

                    //3、设置当前商品的秒杀时间信息
                    redisTo.setStartTime(session.getStartTime().getTime());
                    redisTo.setEndTime(session.getEndTime().getTime());

                    //4、设置商品的随机码（防止恶意攻击）
                    redisTo.setRandomCode(token);

                    //序列化json格式存入Redis中
                    String seckillValue = JSON.toJSONString(redisTo);
                    operations.put(seckillSkuVo.getPromotionSessionId().toString() + "-" + seckillSkuVo.getSkuId().toString(),seckillValue);

                    //如果当前这个场次的商品库存信息已经上架就不需要上架
                    //5、使用库存作为分布式Redisson信号量（限流）
                    // 使用库存作为分布式信号量
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    // 商品可以秒杀的数量作为信号量
                    semaphore.trySetPermits(seckillSkuVo.getSeckillCount());
                }
            });
        });
    }

    /**
     * 获取到当前可以参加秒杀商品的信息
     * @return
     */
    @SentinelResource(value = "getCurrentSeckillSkusResource",blockHandler = "blockHandler")
    @Override
    public List<SeckillSkuRedisTo> getCurrentSeckillSkus() {
        try (Entry entry = SphU.entry("seckillSkus")) {
            //1、确定当前属于哪个秒杀场次
            long currentTime = System.currentTimeMillis();

            //从Redis中查询到所有key以seckill:sessions开头的所有数据
            Set<String> keys = RedisUtil.keys(GlobalConfig.redisPrefix + SESSION_CACHE_PREFIX + "*");
            for (String key : keys) {
                //seckill:sessions:1594396764000_1594453242000
                String replace = key.replace(SESSION_CACHE_PREFIX, "");
                String[] s = replace.split("_");
                //获取存入Redis商品的开始时间
                long startTime = Long.parseLong(s[0]);
                //获取存入Redis商品的结束时间
                long endTime = Long.parseLong(s[1]);

                //判断是否是当前秒杀场次
                if (currentTime >= startTime && currentTime <= endTime) {
                    //2、获取这个秒杀场次需要的所有商品信息
                    List<String> range = RedisUtil.handler().opsForList().range(key, -100, 100).stream().map(item -> {
                        String items = (String) item;
                        return items;
                    }).collect(Collectors.toList());
                    BoundHashOperations<String, String, String> hasOps = RedisUtil.handler().boundHashOps(GlobalConfig.redisPrefix + SECKILL_CHARE_PREFIX);
                    assert range != null;
                    List<String> listValue = hasOps.multiGet(range);
                    if (listValue != null && listValue.size() >= 0) {

                        List<SeckillSkuRedisTo> collect = listValue.stream().map(item -> {
                            String items = (String) item;
                            SeckillSkuRedisTo redisTo = JSON.parseObject(items, SeckillSkuRedisTo.class);
                            // redisTo.setRandomCode(null);当前秒杀开始需要随机码
                            return redisTo;
                        }).collect(Collectors.toList());
                        return collect;
                    }
                    break;
                }
            }
        } catch (BlockException e) {
            log.error("资源被限流{}",e.getMessage());
        }

        return null;
    }

    @Override
    public SeckillSkuRedisTo getSkuSeckilInfo(Long skuId) {
        return null;
    }

    @Override
    public String kill(String killId, String key, Integer num) throws InterruptedException {
        return null;
    }

//    public List<SeckillSkuRedisTo> blockHandler(SentinelUrlBlockHandler e) {
//
//        log.error("getCurrentSeckillSkusResource被限流了,{}",e.getMessage());
//        return null;
//    }
}
