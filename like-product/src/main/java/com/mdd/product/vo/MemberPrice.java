package com.mdd.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: server
 * @description: spu保存
 * @author: Claire
 * @create: 2022-11-15 15:24
 **/
@Data
public class MemberPrice {
    private Long id;
    private String name;
    private BigDecimal price;
}