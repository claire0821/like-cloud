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

/**
 * 品牌分类关联参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CategoryBrandRelationParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "brandId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "brandId参数值不能少于0", groups = {create.class, update.class})
    private Long brandId;
    @NotNull(message = "catelogId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "catelogId参数值不能少于0", groups = {create.class, update.class})
    private Long catelogId;
    @NotNull(message = "brandName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "brandName参数不能超出255个字符", groups = {create.class, update.class})
    private String brandName;
    @NotNull(message = "catelogName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "catelogName参数不能超出255个字符", groups = {create.class, update.class})
    private String catelogName;
}
