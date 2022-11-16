package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * CouponHistoryVo
 */
@Data
public class CouponHistoryDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long couponId;  // 优惠券id
    private Long memberId;  // 会员id
    private String memberNickName;  // 会员名字
    private Integer getType;  // 获取方式[0->后台赠送；1->主动领取]
    private Integer useType;  // 使用状态[0->未使用；1->已使用；2->已过期]
    private Date useTime;  // 使用时间
    private Long orderId;  // 订单id
    private Long orderSn;  // 订单号

}
