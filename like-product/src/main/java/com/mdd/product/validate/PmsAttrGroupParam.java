package com.mdd.product.validate;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 属性分组参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsAttrGroupParam extends BaseParam {

    @IDMust(message = "attrGroupId参数必传且需大于0", groups = {update.class, delete.class})
    private Long attrGroupId;

    @NotNull(message = "attrGroupName参数缺失", groups = {create.class, update.class})
    @Length(max = 20, message = "attrGroupName参数不能超出20个字符", groups = {create.class, update.class})
    private String attrGroupName;

    @NotNull(message = "sort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sort参数值不能少于0", groups = {create.class, update.class})
    private Long sort;

    @NotNull(message = "descript参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "descript参数不能超出255个字符", groups = {create.class, update.class})
    private String descript;

    @NotNull(message = "icon参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "icon参数不能超出255个字符", groups = {create.class, update.class})
    private String icon;

    @NotNull(message = "catelogId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "catelogId参数值不能少于0", groups = {create.class, update.class})
    private Long catelogId;

}
