package com.mdd.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-15 17:01
 **/
@Data
public class SpuBoundTo {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
