package com.mdd.admin.validate.notice_user;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 用户系统通知参数
 * @author Claire
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserSystemNoticeParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "readState参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "readState参数值不能少于0", groups = {create.class, update.class})
    private Integer readState;

    @NotNull(message = "pushState参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "pushState参数值不能少于0", groups = {create.class, update.class})
    private Integer pushState;

    @NotNull(message = "systemNoticeId参数缺失", groups = {create.class, update.class})
    private Integer systemNoticeId;

    @NotNull(message = "readTime参数缺失", groups = {create.class, update.class})
    private Long readTime;

    @NotNull(message = "pullTime参数缺失", groups = {create.class, update.class})
    private Long pullTime;

}
