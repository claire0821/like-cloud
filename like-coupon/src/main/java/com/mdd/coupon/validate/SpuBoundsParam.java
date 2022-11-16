package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * 商品spu积分设置参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpuBoundsParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;
    @NotNull(message = "growBounds参数缺失", groups = {create.class, update.class})
    private BigDecimal growBounds;
    @NotNull(message = "buyBounds参数缺失", groups = {create.class, update.class})
    private BigDecimal buyBounds;
    @NotNull(message = "work参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "work参数值不能少于0", groups = {create.class, update.class})
    private Integer work;
}
