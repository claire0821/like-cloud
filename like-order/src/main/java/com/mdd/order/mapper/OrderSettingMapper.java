package com.mdd.order.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.order.entity.OrderSetting;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息Mapper
 */
@Mapper
public interface OrderSettingMapper extends IBaseMapper<OrderSetting> {
}
