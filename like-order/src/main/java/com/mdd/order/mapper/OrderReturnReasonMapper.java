package com.mdd.order.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.order.entity.OrderReturnReason;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因Mapper
 */
@Mapper
public interface OrderReturnReasonMapper extends IBaseMapper<OrderReturnReason> {
}
