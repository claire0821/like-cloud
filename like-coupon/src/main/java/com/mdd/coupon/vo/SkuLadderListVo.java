package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * SkuLadderVo
 */
@Data
public class SkuLadderListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // spu_id
    private Integer fullCount;  // 满几件
    private BigDecimal discount;  // 打几折
    private BigDecimal price;  // 折后价
    private Integer addOther;  // 是否叠加其他优惠[0-不可叠加，1-可叠加]

}
