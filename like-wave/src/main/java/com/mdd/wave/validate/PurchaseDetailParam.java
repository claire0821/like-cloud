package com.mdd.wave.validate;

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

/**
 * 【请填写功能名称】参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseDetailParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "purchaseId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "purchaseId参数值不能少于0", groups = {create.class, update.class})
    private Long purchaseId;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "skuNum参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuNum参数值不能少于0", groups = {create.class, update.class})
    private Integer skuNum;
    @NotNull(message = "skuPrice参数缺失", groups = {create.class, update.class})
    private BigDecimal skuPrice;
    @NotNull(message = "wareId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "wareId参数值不能少于0", groups = {create.class, update.class})
    private Long wareId;
    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;
}
