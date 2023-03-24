package com.mdd.admin.validate.system;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 系统角色参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemAuthRoleParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "缺少参数name", groups = {create.class, update.class})
    @Length(min = 1, max = 30, message = "角色名称必须在1~30个字符内", groups = {create.class, update.class})
    @Pattern(message = "不能含有特殊符号:或者;", regexp="[^:;]",groups = {create.class, update.class})
    private String name;

    @Max(value = 200, message = "备注信息不能超过200个字符")
    private String remark = "";

    @NotNull(message = "排序号不能为空", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer sort;

    @NotNull(message = "请选择状态", groups = {create.class, update.class})
    private Integer isDisable;

    private String menuIds = "";

}
