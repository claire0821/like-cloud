package com.mdd.order.vo;

import lombok.Data;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 14:34
 **/
@Data
public class PayVo {

    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}
