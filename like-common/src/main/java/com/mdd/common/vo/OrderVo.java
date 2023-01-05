package com.mdd.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 13:36
 **/
@Data
public class OrderVo {

    private String orderSn; // 订单号
    private Long couponId; // 使用的优惠券
    private Integer status; // 订单状态
}
