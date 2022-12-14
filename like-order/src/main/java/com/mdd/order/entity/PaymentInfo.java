package com.mdd.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * 支付信息实体
 */
@Data
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String orderSn; // 订单号（对外业务号）
    private Long orderId; // 订单id
    private String alipayTradeNo; // 支付宝交易流水号
    private BigDecimal totalAmount; // 支付总金额
    private String subject; // 交易内容
    private String paymentStatus; // 支付状态
    private Date createTime; // 创建时间
    private Date confirmTime; // 确认时间
    private String callbackContent; // 回调内容
    private Date callbackTime; // 回调时间

}