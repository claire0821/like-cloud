package com.mdd.coupon.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.coupon.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息Mapper
 */
@Mapper
public interface CouponMapper extends IBaseMapper<Coupon> {
}
