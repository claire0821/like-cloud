package com.mdd.order.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.order.entity.OrderOperateHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录Mapper
 */
@Mapper
public interface OrderOperateHistoryMapper extends IBaseMapper<OrderOperateHistory> {
}
