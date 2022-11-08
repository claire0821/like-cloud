package com.mdd.member.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * 会员参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsMemberParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "levelId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "levelId参数值不能少于0", groups = {create.class, update.class})
    private Long levelId;

    @NotNull(message = "username参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "username参数不能超出64个字符", groups = {create.class, update.class})
    private String username;

    @NotNull(message = "password参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "password参数不能超出64个字符", groups = {create.class, update.class})
    private String password;

    @NotNull(message = "nickname参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "nickname参数不能超出64个字符", groups = {create.class, update.class})
    private String nickname;

    @NotNull(message = "mobile参数缺失", groups = {create.class, update.class})
    @Length(max = 20, message = "mobile参数不能超出20个字符", groups = {create.class, update.class})
    private String mobile;

    @NotNull(message = "email参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "email参数不能超出64个字符", groups = {create.class, update.class})
    private String email;

    @NotNull(message = "header参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "header参数不能超出500个字符", groups = {create.class, update.class})
    private String header;

    @NotNull(message = "gender参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "gender参数值不能少于0", groups = {create.class, update.class})
    private Long gender;

    @NotNull(message = "birth参数缺失", groups = {create.class, update.class})
    private Date birth;

    @NotNull(message = "city参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "city参数不能超出500个字符", groups = {create.class, update.class})
    private String city;

    @NotNull(message = "job参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "job参数不能超出255个字符", groups = {create.class, update.class})
    private String job;

    @NotNull(message = "sign参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "sign参数不能超出255个字符", groups = {create.class, update.class})
    private String sign;

    @NotNull(message = "sourceType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sourceType参数值不能少于0", groups = {create.class, update.class})
    private Long sourceType;

    @NotNull(message = "integration参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "integration参数值不能少于0", groups = {create.class, update.class})
    private Long integration;

    @NotNull(message = "growth参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "growth参数值不能少于0", groups = {create.class, update.class})
    private Long growth;

    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Long status;

}
