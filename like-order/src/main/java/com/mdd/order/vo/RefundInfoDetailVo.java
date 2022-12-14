package com.mdd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * RefundInfoVo
 */
@Data
public class RefundInfoDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long orderReturnId;  // 退款的订单
    private BigDecimal refund;  // 退款金额
    private String refundSn;  // 退款交易流水号
    private Integer refundStatus;  // 退款状态
    private Integer refundChannel;  // 退款渠道[1-支付宝，2-微信，3-银联，4-汇款]
    private String refundContent;  // 

}
