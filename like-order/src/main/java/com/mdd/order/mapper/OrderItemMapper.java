package com.mdd.order.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息Mapper
 */
@Mapper
public interface OrderItemMapper extends IBaseMapper<OrderItem> {
}
