package com.mdd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * PaymentInfoVo
 */
@Data
public class PaymentInfoDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String orderSn;  // 订单号（对外业务号）
    private Long orderId;  // 订单id
    private String alipayTradeNo;  // 支付宝交易流水号
    private BigDecimal totalAmount;  // 支付总金额
    private String subject;  // 交易内容
    private String paymentStatus;  // 支付状态
    private Date confirmTime;  // 确认时间
    private String callbackContent;  // 回调内容
    private Date callbackTime;  // 回调时间

}
