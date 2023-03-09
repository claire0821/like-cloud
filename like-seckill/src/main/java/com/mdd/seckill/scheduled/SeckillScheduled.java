package com.mdd.seckill.scheduled;

import com.mdd.common.config.GlobalConfig;
import com.mdd.seckill.service.ISeckillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 17:21
 **/
@Slf4j
@Service
public class SeckillScheduled {

    @Autowired
    private ISeckillService iSeckillService;

    @Autowired
    private RedissonClient redissonClient;

    //秒杀商品上架功能的锁
    private final String upload_lock = "seckill:upload:lock";

    //TODO 保证幂等性问题
    // @Scheduled(cron = "*/5 * * * * ? ")
    @Scheduled(cron = "0 0 1/1 * * ? ")
    public void uploadSeckillSkuLatest3Days() {
        //1、重复上架无需处理
        log.info("上架秒杀的商品...");

        //分布式锁
        RLock lock = redissonClient.getLock(GlobalConfig.redisPrefix +  upload_lock);
        try {
            //加锁
            lock.lock(10, TimeUnit.SECONDS);
            iSeckillService.uploadSeckillSkuLatest3Days();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
