package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券与产品关联实体
 */
@Data
public class CouponSpuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long couponId; // 优惠券id
    private Long spuId; // spu_id
    private String spuName; // spu_name

}