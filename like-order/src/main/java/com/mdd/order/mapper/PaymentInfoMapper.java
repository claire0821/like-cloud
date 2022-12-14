package com.mdd.order.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.order.entity.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息Mapper
 */
@Mapper
public interface PaymentInfoMapper extends IBaseMapper<PaymentInfo> {
}
