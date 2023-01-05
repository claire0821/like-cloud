package com.mdd.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: server
 * @description: 封装订单提交数据的vo
 * @author: Claire
 * @create: 2022-12-20 14:25
 **/
@Data
public class OrderSubmitVo {
    private String orderSn;  // 订单号
    /** 收获地址的id **/
    private Long addrId;

    /** 支付方式 **/
    private Integer payType;
    //无需提交要购买的商品，去购物车再获取一遍
    //优惠、发票

    /** 防重令牌 **/
    private String orderToken;

    /** 应付价格 **/
    private BigDecimal payPrice;

    /** 订单备注 **/
    private String note;
}
