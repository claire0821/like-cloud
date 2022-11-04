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
 * 属性&属性分组关联参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsAttrAttrgroupRelationParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "attrId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attrId参数值不能少于0", groups = {create.class, update.class})
    private Long attrId;

    @NotNull(message = "attrGroupId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attrGroupId参数值不能少于0", groups = {create.class, update.class})
    private Long attrGroupId;

    @NotNull(message = "attrSort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attrSort参数值不能少于0", groups = {create.class, update.class})
    private Long attrSort;

}
