package com.mdd.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-15 17:00
 **/
@Data
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;
}