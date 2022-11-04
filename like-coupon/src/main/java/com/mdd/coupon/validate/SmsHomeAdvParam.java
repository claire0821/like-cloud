package com.mdd.coupon.validate;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * 首页轮播广告参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsHomeAdvParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "name参数不能超出100个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "pic参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "pic参数不能超出500个字符", groups = {create.class, update.class})
    private String pic;

    @NotNull(message = "startTime参数缺失", groups = {create.class, update.class})
    private Date startTime;

    @NotNull(message = "endTime参数缺失", groups = {create.class, update.class})
    private Date endTime;

    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;

    @NotNull(message = "clickCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "clickCount参数值不能少于0", groups = {create.class, update.class})
    private Long clickCount;

    @NotNull(message = "url参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "url参数不能超出500个字符", groups = {create.class, update.class})
    private String url;

    @NotNull(message = "note参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "note参数不能超出500个字符", groups = {create.class, update.class})
    private String note;

    @NotNull(message = "sort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sort参数值不能少于0", groups = {create.class, update.class})
    private Long sort;

    @NotNull(message = "publisherId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "publisherId参数值不能少于0", groups = {create.class, update.class})
    private Long publisherId;

    @NotNull(message = "authId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "authId参数值不能少于0", groups = {create.class, update.class})
    private Long authId;

}
