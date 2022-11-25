package com.mdd.member.validate;

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
 * 会员收货地址参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberReceiveAddressParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;
    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "name参数不能超出255个字符", groups = {create.class, update.class})
    private String name;
    @NotNull(message = "phone参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "phone参数不能超出64个字符", groups = {create.class, update.class})
    private String phone;
    @NotNull(message = "postCode参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "postCode参数不能超出64个字符", groups = {create.class, update.class})
    private String postCode;
    @NotNull(message = "province参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "province参数不能超出100个字符", groups = {create.class, update.class})
    private String province;
    @NotNull(message = "city参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "city参数不能超出100个字符", groups = {create.class, update.class})
    private String city;
    @NotNull(message = "region参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "region参数不能超出100个字符", groups = {create.class, update.class})
    private String region;
    @NotNull(message = "detailAddress参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "detailAddress参数不能超出255个字符", groups = {create.class, update.class})
    private String detailAddress;
    @NotNull(message = "areacode参数缺失", groups = {create.class, update.class})
    @Length(max = 15, message = "areacode参数不能超出15个字符", groups = {create.class, update.class})
    private String areacode;
    @NotNull(message = "defaultStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "defaultStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer defaultStatus;
}
