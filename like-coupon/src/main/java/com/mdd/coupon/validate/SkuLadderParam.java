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
 * 商品阶梯价格参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SkuLadderParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "fullCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "fullCount参数值不能少于0", groups = {create.class, update.class})
    private Integer fullCount;
    @NotNull(message = "discount参数缺失", groups = {create.class, update.class})
    private BigDecimal discount;
    @NotNull(message = "price参数缺失", groups = {create.class, update.class})
    private BigDecimal price;
    @NotNull(message = "addOther参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "addOther参数值不能少于0", groups = {create.class, update.class})
    private Integer addOther;
}
