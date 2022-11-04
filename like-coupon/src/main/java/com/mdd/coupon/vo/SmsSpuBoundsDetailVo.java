package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * SmsSpuBoundsVo
 */
@Data
public class SmsSpuBoundsDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long spuId;  // 
    private BigDecimal growBounds;  // 成长积分
    private BigDecimal buyBounds;  // 购物积分
    private Integer work;  // 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]

}
