package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SmsMemberPriceVo
 */
@Data
public class SmsMemberPriceDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private Long memberLevelId;  // 会员等级id
    private String memberLevelName;  // 会员等级名
    private BigDecimal memberPrice;  // 会员对应价格
    private Integer addOther;  // 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]

}
