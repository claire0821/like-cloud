package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品满减信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsSkuFullReductionParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;

    @NotNull(message = "fullPrice参数缺失", groups = {create.class, update.class})
    private BigDecimal fullPrice;

    @NotNull(message = "reducePrice参数缺失", groups = {create.class, update.class})
    private BigDecimal reducePrice;

    @NotNull(message = "addOther参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "addOther参数值不能少于0", groups = {create.class, update.class})
    private Integer addOther;

}
