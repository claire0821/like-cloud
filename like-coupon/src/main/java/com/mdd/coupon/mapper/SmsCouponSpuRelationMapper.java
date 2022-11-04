package com.mdd.coupon.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.coupon.entity.SmsCouponSpuRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联Mapper
 */
@Mapper
public interface SmsCouponSpuRelationMapper extends IBaseMapper<SmsCouponSpuRelation> {
}
