package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.to.mq.SeckillOrderTo;
import com.mdd.common.validate.PageParam;
import com.mdd.common.vo.OrderVo;
import com.mdd.order.entity.Order;
import com.mdd.order.to.OrderCreateTo;
import com.mdd.order.validate.OrderParam;
import com.mdd.order.vo.OrderListVo;
import com.mdd.order.vo.OrderDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.vo.OrderSubmitVo;
import com.mdd.order.vo.PayAsyncVo;

import java.util.Map;

/**
 * 订单服务接口类
 */
public interface IOrderService extends IService<Order> {

    /**
     * 订单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderVo>
     */
    PageResult<OrderListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 订单详情
     *
     * @param id 主键ID
     * @return Order
     */
    OrderDetailVo detail(Long id);

    /**
     * 订单新增
     *
     * @param orderParam 参数
     */
    void add(OrderParam orderParam);

    /**
     * 订单编辑
     *
     * @param orderParam 参数
     */
    void edit(OrderParam orderParam);

    /**
     * 订单删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    OrderCreateTo createOrder();

    void submitOrder(OrderSubmitVo orderSubmitVo);

    OrderVo getOrderByOrderSn(String orderSn);

    /**
     * 创建秒杀单
     * @param orderTo
     */
    void createSeckillOrder(SeckillOrderTo orderTo);

    /**
     * 关闭订单
     * @param orderEntity
     */
    void closeOrder(Order orderEntity);

    String handlePayResult(PayAsyncVo asyncVo);

    /**
     * 微信异步通知处理
     * @param notifyData
     */
    String asyncNotify(String notifyData);
}
