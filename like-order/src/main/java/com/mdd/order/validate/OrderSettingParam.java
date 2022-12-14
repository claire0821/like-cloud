package com.mdd.order.validate;

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
 * 订单配置信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderSettingParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "flashOrderOvertime参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "flashOrderOvertime参数值不能少于0", groups = {create.class, update.class})
    private Integer flashOrderOvertime;
    @NotNull(message = "normalOrderOvertime参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "normalOrderOvertime参数值不能少于0", groups = {create.class, update.class})
    private Integer normalOrderOvertime;
    @NotNull(message = "confirmOvertime参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "confirmOvertime参数值不能少于0", groups = {create.class, update.class})
    private Integer confirmOvertime;
    @NotNull(message = "finishOvertime参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "finishOvertime参数值不能少于0", groups = {create.class, update.class})
    private Integer finishOvertime;
    @NotNull(message = "commentOvertime参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentOvertime参数值不能少于0", groups = {create.class, update.class})
    private Integer commentOvertime;
    @NotNull(message = "memberLevel参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberLevel参数值不能少于0", groups = {create.class, update.class})
    private Integer memberLevel;
}
