package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * CouponVo
 */
@Data
public class CouponListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Integer couponType;  // 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
    private String couponImg;  // 优惠券图片
    private String couponName;  // 优惠卷名字
    private Integer num;  // 数量
    private BigDecimal amount;  // 金额
    private Integer perLimit;  // 每人限领张数
    private BigDecimal minPoint;  // 使用门槛
    private String startTime; // 开始时间
    private String endTime; // 结束时间
    private Integer useType;  // 使用类型[0->全场通用；1->指定分类；2->指定商品]
    private String note;  // 备注
    private Integer publishCount;  // 发行数量
    private Integer useCount;  // 已使用数量
    private Integer receiveCount;  // 领取数量
    private Date enableStartTime;  // 可以领取的开始日期
    private Date enableEndTime;  // 可以领取的结束日期
    private String code;  // 优惠码
    private Integer memberLevel;  // 可以领取的会员等级[0->不限等级，其他-对应等级]
    private Integer publish;  // 发布状态[0-未发布，1-已发布]

}
