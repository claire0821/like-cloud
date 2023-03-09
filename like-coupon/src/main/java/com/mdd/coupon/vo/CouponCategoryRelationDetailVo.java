package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * CouponSpuCategoryRelationVo
 */
@Data
public class CouponCategoryRelationDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long couponId;  // 优惠券id
    private Long categoryId;  // 产品分类id
    private String categoryName;  // 产品分类名称

}
