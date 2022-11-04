package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券领取历史记录实体
 */
@Data
public class SmsCouponHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long couponId; // 优惠券id
    private Long memberId; // 会员id
    private String memberNickName; // 会员名字
    private Integer getType; // 获取方式[0->后台赠送；1->主动领取]
    private Date createTime; // 创建时间
    private Integer useType; // 使用状态[0->未使用；1->已使用；2->已过期]
    private Date useTime; // 使用时间
    private Long orderId; // 订单id
    private Long orderSn; // 订单号

}