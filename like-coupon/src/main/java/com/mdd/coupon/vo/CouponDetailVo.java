package com.mdd.coupon.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CouponDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 优惠码
     */
    private String code;
    /**
     * 优惠券图片
     */
    private String couponImg;
    /**
     * 优惠卷名字
     */
    private String couponName;
    /**
     * 优惠券金额
     */
    private BigDecimal amount;
    /**
     * 发送总量类型：1-不限制；2-限制张数 PublishCountTypeEnum
     */
    private Integer publishCountType;
    /**
     * 发送总量类型为2时：该字段为限制的张数
     */
    private Integer publishCount;
    /**
     * 已使用数量
     */
    private Integer useCount;
    /**
     * 已领取数量
     */
    private Integer receiveCount;
    /**
     * 使用条件类型：1-无门槛；2-订单满足金额 ConditionTypeEnum
     */
    private Integer conditionType;
    /**
     * 使用条件类型为2时：该字段为订单满足金额可使用
     */
    private BigDecimal conditionMoney;
    /**
     *  用券时间类型：1-固定时间；2-领券当天起；3-领券次日起 UseTimeTypeEnum
     */
    private Integer useTimeType;
    /**
     * 用券时间类型为1时：该字段为使用开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useTimeStart;
    /**
     * 用券时间类型为1时：该字段为使用结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useTimeEnd;
    /**
     * 用券时间类型为2、3时：该字段为多少天内可用
     */
    private Integer useTime;
    /**
     * 领取类型：1-直接领取；2-平台赠送；3-活动赠送 GetTypeEnum
     */
    private Integer getType;
    /**
     * 领取次数类型：1-不限制领取传次数；2-限制次数；3-每天限制数量
     */
    private Integer getCountType;
    /**
     * 领取次数类型为：2、3时：该字段为领取限制的数量
     */
    private Integer getCount;
    /**
     * 发放开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enableStartTime;
    /**
     * 发放结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enableEndTime;
    /**
     * 适用商品类型:1-全场通用；2-指定分类；3-指定商品；4-指定商品不可用 UseTypeEnum
     */
    private Integer useType;
    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    private Integer memberLevel;
    /**
     * 备注
     */
    private String note;
    /**
     * 优惠券状态：1-未发布；2-已发布; 3-结束 SatusEnum
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
