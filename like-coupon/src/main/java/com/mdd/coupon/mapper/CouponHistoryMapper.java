package com.mdd.coupon.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.coupon.entity.CouponHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录Mapper
 */
@Mapper
public interface CouponHistoryMapper extends IBaseMapper<CouponHistory> {
}
