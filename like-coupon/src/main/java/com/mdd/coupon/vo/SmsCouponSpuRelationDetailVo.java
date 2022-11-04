package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SmsCouponSpuRelationVo
 */
@Data
public class SmsCouponSpuRelationDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long couponId;  // 优惠券id
    private Long spuId;  // spu_id
    private String spuName;  // spu_name

}
