package com.mdd.product.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * sku销售属性&值参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsSkuSaleAttrValueParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;

    @NotNull(message = "attrId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attrId参数值不能少于0", groups = {create.class, update.class})
    private Long attrId;

    @NotNull(message = "attrName参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "attrName参数不能超出200个字符", groups = {create.class, update.class})
    private String attrName;

    @NotNull(message = "attrValue参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "attrValue参数不能超出200个字符", groups = {create.class, update.class})
    private String attrValue;

    @NotNull(message = "attrSort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attrSort参数值不能少于0", groups = {create.class, update.class})
    private Long attrSort;

}
