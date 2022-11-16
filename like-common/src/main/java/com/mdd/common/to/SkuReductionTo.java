package com.mdd.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-15 17:01
 **/
@Data
public class SkuReductionTo {

    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
