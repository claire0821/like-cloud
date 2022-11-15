package com.mdd.product.validate;

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
 * sku信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SkuInfoParam extends BaseParam {

    @IDLongMust(message = "skuId参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long skuId;
    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;
    @NotNull(message = "skuName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "skuName参数不能超出255个字符", groups = {create.class, update.class})
    private String skuName;
    @NotNull(message = "skuDesc参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "skuDesc参数不能超出2000个字符", groups = {create.class, update.class})
    private String skuDesc;
    @NotNull(message = "catalogId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "catalogId参数值不能少于0", groups = {create.class, update.class})
    private Long catalogId;
    @NotNull(message = "brandId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "brandId参数值不能少于0", groups = {create.class, update.class})
    private Long brandId;
    @NotNull(message = "skuDefaultImg参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "skuDefaultImg参数不能超出255个字符", groups = {create.class, update.class})
    private String skuDefaultImg;
    @NotNull(message = "skuTitle参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "skuTitle参数不能超出255个字符", groups = {create.class, update.class})
    private String skuTitle;
    @NotNull(message = "skuSubtitle参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "skuSubtitle参数不能超出2000个字符", groups = {create.class, update.class})
    private String skuSubtitle;
    @NotNull(message = "price参数缺失", groups = {create.class, update.class})
    private BigDecimal price;
    @NotNull(message = "saleCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "saleCount参数值不能少于0", groups = {create.class, update.class})
    private Long saleCount;
}
