package com.mdd.order.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单Mapper
 */
@Mapper
public interface OrderMapper extends IBaseMapper<Order> {
}
