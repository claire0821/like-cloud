package com.mdd.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.common.constant.OrderConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.BaseException;
import com.mdd.common.to.OrderTo;
import com.mdd.common.to.mq.StockDetailTo;
import com.mdd.common.to.mq.StockLockedTo;
import com.mdd.common.vo.CartItemVo;
import com.mdd.common.vo.OrderVo;
import com.mdd.common.vo.WareSkuLockVo;
import com.mdd.ware.entity.WareOrderTask;
import com.mdd.ware.entity.WareOrderTaskDetail;
import com.mdd.ware.feign.IOrderFeignService;
import com.mdd.ware.feign.IProductFeignService;
import com.mdd.ware.service.IWareOrderTaskDetailService;
import com.mdd.ware.service.IWareOrderTaskService;
import com.mdd.ware.service.IWareSkuService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.validate.WareSkuParam;
import com.mdd.ware.vo.SkuHasStockVo;
import com.mdd.ware.vo.WareSkuListVo;
import com.mdd.ware.vo.WareSkuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.ware.entity.WareSku;
import com.mdd.ware.mapper.WareSkuMapper;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品库存实现类
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper,WareSku> implements IWareSkuService {
        
    @Resource
    WareSkuMapper wareSkuMapper;
    @Autowired
    IProductFeignService IProductFeignService;
    @Autowired
    IOrderFeignService iOrderFeignService;
    @Autowired
    IWareOrderTaskService iWareOrderTaskService;
    @Autowired
    IWareOrderTaskDetailService iWareOrderTaskDetailService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     * 商品库存列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareSkuListVo>
     */
    @Override
    public PageResult<WareSkuListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        wareSkuMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:wareId@ware_id:long",
            "=:stock:int",
            "like:skuName@sku_name:str",
            "=:stockLocked@stock_locked:int",
        });

        IPage<WareSku> iPage = wareSkuMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<WareSkuListVo> list = new LinkedList<>();
        for(WareSku item : iPage.getRecords()) {
            WareSkuListVo vo = new WareSkuListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品库存详情
     *
     * @param id 主键参数
     * @return WareSku
     */
    @Override
    public WareSkuDetailVo detail(Long id) {
        WareSku model = wareSkuMapper.selectOne(
                new QueryWrapper<WareSku>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        WareSkuDetailVo vo = new WareSkuDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品库存新增
     *
     * @param wareSkuParam 参数
     */
    @Override
    public void add(WareSkuParam wareSkuParam) {
        WareSku model = new WareSku();
        model.setSkuId(wareSkuParam.getSkuId());
        model.setWareId(wareSkuParam.getWareId());
        model.setStock(wareSkuParam.getStock());
        model.setSkuName(wareSkuParam.getSkuName());
        model.setStockLocked(wareSkuParam.getStockLocked());
        wareSkuMapper.insert(model);
    }

    /**
     * 商品库存编辑
     *
     * @param wareSkuParam 参数
     */
    @Override
    public void edit(WareSkuParam wareSkuParam) {
        WareSku model = wareSkuMapper.selectOne(
                new QueryWrapper<WareSku>()
                    .eq("id",  wareSkuParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(wareSkuParam.getId());
        model.setSkuId(wareSkuParam.getSkuId());
        model.setWareId(wareSkuParam.getWareId());
        model.setStock(wareSkuParam.getStock());
        model.setSkuName(wareSkuParam.getSkuName());
        model.setStockLocked(wareSkuParam.getStockLocked());
        wareSkuMapper.updateById(model);
    }

    /**
     * 商品库存删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        WareSku model = wareSkuMapper.selectOne(
                new QueryWrapper<WareSku>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        wareSkuMapper.delete(new QueryWrapper<WareSku>().eq("id", id));
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断如果还没有这个库存记录新增
        final List<WareSku> wareSkus = wareSkuMapper.selectList(new QueryWrapper<WareSku>()
                .eq("sku_id", skuId).eq("ware_id", wareId));
        if(wareSkus == null || wareSkus.size() <= 0) {
            WareSku wareSku = new WareSku();
            wareSku.setSkuId(skuId);
            wareSku.setStock(skuNum);
            wareSku.setWareId(wareId);
            wareSku.setStockLocked(0);
            //TODO 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            //TODO 还可以用什么办法让异常出现以后不回滚？高级
            try{
                final LinkedHashMap detail = (LinkedHashMap) IProductFeignService.detail(skuId);
                final Integer code = (Integer) detail.get("code");
                if(code == 0) {
                    final LinkedHashMap data = (LinkedHashMap) detail.get("data");
                    final String skuName = (String) data.get("skuName");
                    wareSku.setSkuName(skuName);
                }
            } catch (Exception e) {

            }
            wareSkuMapper.insert(wareSku);
        } else {
            for (WareSku skus : wareSkus) {
                skus.setStock(skus.getStock() + skuNum);
                wareSkuMapper.updateById(skus);
            }
        }
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        final List<SkuHasStockVo> collect = skuIds.stream().map(item -> {
            final WareSku byId = this.getById(item);
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(byId.getStock() - byId.getStockLocked() > 0 ? true : false);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean orderLockStock(WareSkuLockVo vo) {
        /**
         * 保存库存工作单详情信息
         * 追溯
         */
        WareOrderTask wareOrderTask = new WareOrderTask();
        wareOrderTask.setOrderSn(vo.getOrderSn());
        wareOrderTask.setCreateTime(new Date());
        iWareOrderTaskService.save(wareOrderTask);

        //1、按照下单的收货地址，找到一个就近仓库，锁定库存
        //2、找到每个商品在哪个仓库都有库存
        final List<CartItemVo> locks = vo.getLocks();
        List<SkuWareHasStock> collect = locks.stream().map((item) -> {
            SkuWareHasStock stock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            stock.setSkuId(skuId);
            stock.setNum(item.getSkuQuantity());
            //查询这个商品在哪个仓库有库存
            List<Long> wareIdList = this.wareSkuMapper.listWareIdHasSkuStock(skuId);
            stock.setWareId(wareIdList);

            return stock;
        }).collect(Collectors.toList());

        //2、锁定库存
        for (SkuWareHasStock hasStock : collect) {
            boolean skuStocked = false;
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareId();

            if (org.springframework.util.StringUtils.isEmpty(wareIds)) {
                //没有任何仓库有这个商品的库存
                //TODO 商品名称
                throw new BaseException(HttpEnum.ORDER_NO_STOCK.getCode(), HttpEnum.ORDER_NO_STOCK.getMsg());
            }

            //1、如果每一个商品都锁定成功,将当前商品锁定了几件的工作单记录发给MQ
            //2、锁定失败。前面保存的工作单信息都回滚了。发送出去的消息，即使要解锁库存，由于在数据库查不到指定的id，所有就不用解锁
            for (Long wareId : wareIds) {
                //锁定成功就返回1，失败就返回0
                Long count = this.wareSkuMapper.lockSkuStock(skuId,wareId,hasStock.getNum());
                if (count == 1) {
                    skuStocked = true;
                    WareOrderTaskDetail taskDetailEntity = WareOrderTaskDetail.builder()
                            .skuId(skuId)
                            .skuName("")
                            .skuNum(hasStock.getNum())
                            .taskId(wareOrderTask.getId())
                            .wareId(wareId)
                            .lockStatus(1)
                            .build();
                    iWareOrderTaskDetailService.save(taskDetailEntity);

                    //TODO 告诉MQ库存锁定成功
                    StockLockedTo lockedTo = new StockLockedTo();
                    lockedTo.setId(wareOrderTask.getId());
                    StockDetailTo detailTo = new StockDetailTo();
                    BeanUtils.copyProperties(taskDetailEntity,detailTo);
                    lockedTo.setDetailTo(detailTo);
                    rabbitTemplate.convertAndSend("stock-event-exchange","stock.locked",lockedTo);
                    break;
                } else {
                    //当前仓库锁失败，重试下一个仓库
                }
            }

            if (skuStocked == false) {
                //当前商品所有仓库都没有锁住
                throw new BaseException(HttpEnum.ORDER_NO_STOCK.getCode(), HttpEnum.ORDER_NO_STOCK.getMsg());
            }
        }

        //3、肯定全部都是锁定成功的
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unlockStock(StockLockedTo to) {
        //库存工作单的id
        StockDetailTo detail = to.getDetailTo();
        Long detailId = detail.getId();

        /**
         * 解锁
         * 1、查询数据库关于这个订单锁定库存信息
         *   有：证明库存锁定成功了
         *      解锁：订单状况
         *          1、没有这个订单，必须解锁库存
         *          2、有这个订单，不一定解锁库存
         *              订单状态：已取消：解锁库存
         *                      已支付：不能解锁库存
         */
        WareOrderTaskDetail taskDetailInfo = iWareOrderTaskDetailService.getById(detailId);
        if(taskDetailInfo == null) {
            return;
        }
        //解锁
        //查出wms_ware_order_task工作单的信息
        Long id = to.getId();
        WareOrderTask orderTaskInfo = iWareOrderTaskService.getById(id);
        //获取订单号查询订单状态
        String orderSn = orderTaskInfo.getOrderSn();
        //远程查询订单信息
        AjaxResult<OrderVo> result = iOrderFeignService.getOrderStatus(orderSn);
        if (result.getCode().equals(HttpEnum.SUCCESS)) {
            //订单数据返回成功
            OrderVo orderInfo = result.getData();

            //判断订单状态是否已取消或者支付或者订单不存在
            if (orderInfo == null || orderInfo.getStatus().equals(OrderConstant.OrderStatusEnum.CREATE_NEW.getCode())
            || orderInfo.getStatus().equals(OrderConstant.OrderStatusEnum.CANCLED.getCode())) {
                //订单已被取消，才能解锁库存
                if (taskDetailInfo.getLockStatus() == 1) {
                    //当前库存工作单详情状态1，已锁定，但是未解锁才可以解锁
                    unLockStock(detail.getSkuId(),detail.getWareId(),detail.getSkuNum(),detailId);
                }
            }
        } else {
            //消息拒绝以后重新放在队列里面，让别人继续消费解锁
            //远程调用服务失败
            throw new RuntimeException("远程调用服务失败");
        }
    }

    /**
     * 防止订单服务卡顿，导致订单状态消息一直改不了，库存优先到期，查订单状态新建，什么都不处理
     * 导致卡顿的订单，永远都不能解锁库存
     * @param orderTo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unlockStock(OrderTo orderTo) {

        String orderSn = orderTo.getOrderSn();
        //查一下最新的库存解锁状态，防止重复解锁库存
        WareOrderTask orderTaskEntity = iWareOrderTaskService.getOrderTaskByOrderSn(orderSn);

        //按照工作单的id找到所有 没有解锁的库存，进行解锁
        Long id = orderTaskEntity.getId();
        List<WareOrderTaskDetail> list = iWareOrderTaskDetailService.list(new QueryWrapper<WareOrderTaskDetail>()
                .eq("task_id", id).eq("lock_status", 1));

        for (WareOrderTaskDetail taskDetailEntity : list) {
            unLockStock(taskDetailEntity.getSkuId(),
                    taskDetailEntity.getWareId(),
                    taskDetailEntity.getSkuNum(),
                    taskDetailEntity.getId());
        }
    }

    /**
     * 解锁库存的方法
     * @param skuId
     * @param wareId
     * @param num
     * @param taskDetailId
     */
    public void unLockStock(Long skuId,Long wareId,Integer num,Long taskDetailId) {
        //库存解锁
        this.wareSkuMapper.unLockStock(skuId,wareId,num);

        //更新工作单的状态
        WareOrderTaskDetail taskDetailEntity = new WareOrderTaskDetail();
        taskDetailEntity.setId(taskDetailId);
        //变为已解锁
        taskDetailEntity.setLockStatus(2);
        iWareOrderTaskDetailService.updateById(taskDetailEntity);
    }

    @Data
    class SkuWareHasStock {
        private Long skuId;
        private Integer num;
        private List<Long> wareId;
    }
}
