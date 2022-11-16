package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券分类关联实体
 */
@Data
public class CouponSpuCategoryRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long couponId; // 优惠券id
    private Long categoryId; // 产品分类id
    private String categoryName; // 产品分类名称

}