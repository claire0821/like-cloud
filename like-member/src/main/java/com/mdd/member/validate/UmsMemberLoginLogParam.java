package com.mdd.member.validate;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 会员登录记录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsMemberLoginLogParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;

    @NotNull(message = "ip参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "ip参数不能超出64个字符", groups = {create.class, update.class})
    private String ip;

    @NotNull(message = "city参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "city参数不能超出64个字符", groups = {create.class, update.class})
    private String city;

    @NotNull(message = "loginType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "loginType参数值不能少于0", groups = {create.class, update.class})
    private Integer loginType;

}
