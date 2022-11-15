package com.mdd.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: server
 * @description: spu保存
 * @author: Claire
 * @create: 2022-11-15 15:20
 **/
@Data
public class Bounds {

    private BigDecimal buyBounds;
    private BigDecimal growBounds;

}