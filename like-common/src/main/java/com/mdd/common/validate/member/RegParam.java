package com.mdd.common.validate.member;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 注册参数类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegParam extends BaseParam {

    @NotNull(message = "username参数缺失")
    @NotEmpty(message = "账号不能为空")
//    @Length(min = 3, max = 12, message = "账号必须在3~12个字符内")
    private String username;

    @NotNull(message = "password参数缺失")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码必须在6~12个字符内")
    @Pattern(message = "账号应该为3-12位数字、字母组合", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,12}$")
    private String password;

    @NotNull(message = "client参数缺失")
    @IntegerContains(values = {1, 2, 3, 4, 5, 6}, message = "不是合法客户端")
    private Integer client;

}
