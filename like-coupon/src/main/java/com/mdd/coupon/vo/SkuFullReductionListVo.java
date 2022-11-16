package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * SkuFullReductionVo
 */
@Data
public class SkuFullReductionListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // spu_id
    private BigDecimal fullPrice;  // 满多少
    private BigDecimal reducePrice;  // 减多少
    private Integer addOther;  // 是否参与其他优惠

}
