package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.OrderItem;
import com.mdd.order.validate.OrderItemParam;
import com.mdd.order.vo.OrderItemListVo;
import com.mdd.order.vo.OrderItemDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 订单项信息服务接口类
 */
public interface IOrderItemService extends IService<OrderItem> {

    /**
     * 订单项信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderItemVo>
     */
    PageResult<OrderItemListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 订单项信息详情
     *
     * @param id 主键ID
     * @return OrderItem
     */
    OrderItemDetailVo detail(Long id);

    /**
     * 订单项信息新增
     *
     * @param orderItemParam 参数
     */
    void add(OrderItemParam orderItemParam);

    /**
     * 订单项信息编辑
     *
     * @param orderItemParam 参数
     */
    void edit(OrderItemParam orderItemParam);

    /**
     * 订单项信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
