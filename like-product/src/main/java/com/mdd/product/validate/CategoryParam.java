package com.mdd.product.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 商品三级分类参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CategoryParam extends BaseParam {

    @IDLongMust(message = "catId参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long catId;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 50, message = "name参数不能超出50个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "parentCid参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "parentCid参数值不能少于0", groups = {create.class, update.class})
    private Long parentCid;

    @NotNull(message = "catLevel参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "catLevel参数值不能少于0", groups = {create.class, update.class})
    private Integer catLevel;

    @NotNull(message = "showStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "showStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer showStatus;

    @NotNull(message = "sort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sort参数值不能少于0", groups = {create.class, update.class})
    private Integer sort;

    @NotNull(message = "icon参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "icon参数不能超出255个字符", groups = {create.class, update.class})
    private String icon;

    @NotNull(message = "productUnit参数缺失", groups = {create.class, update.class})
    @Length(max = 50, message = "productUnit参数不能超出50个字符", groups = {create.class, update.class})
    private String productUnit;

    @NotNull(message = "productCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "productCount参数值不能少于0", groups = {create.class, update.class})
    private Integer productCount;

}
