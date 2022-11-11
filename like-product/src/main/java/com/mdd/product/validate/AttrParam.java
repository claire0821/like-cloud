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
 * 商品属性参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AttrParam extends BaseParam {

    @IDLongMust(message = "attrId参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long attrId;
    @NotNull(message = "attrName参数缺失", groups = {create.class, update.class})
    @Length(max = 30, message = "attrName参数不能超出30个字符", groups = {create.class, update.class})
    private String attrName;
    @NotNull(message = "searchType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "searchType参数值不能少于0", groups = {create.class, update.class})
    private Integer searchType;
    @NotNull(message = "icon参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "icon参数不能超出255个字符", groups = {create.class, update.class})
    private String icon;
    @NotNull(message = "valueSelect参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "valueSelect参数不能超出255个字符", groups = {create.class, update.class})
    private String valueSelect;
    @NotNull(message = "attrType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attrType参数值不能少于0", groups = {create.class, update.class})
    private Integer attrType;
    @NotNull(message = "enable参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "enable参数值不能少于0", groups = {create.class, update.class})
    private Long enable;
    @NotNull(message = "catelogId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "catelogId参数值不能少于0", groups = {create.class, update.class})
    private Long catelogId;
    @NotNull(message = "showDesc参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "showDesc参数值不能少于0", groups = {create.class, update.class})
    private Integer showDesc;
}
