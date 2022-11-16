package com.mdd.coupon.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.coupon.entity.CouponSpuRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联Mapper
 */
@Mapper
public interface CouponSpuRelationMapper extends IBaseMapper<CouponSpuRelation> {
}
