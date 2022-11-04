package com.mdd.coupon.validate;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsCouponParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "couponType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponType参数值不能少于0", groups = {create.class, update.class})
    private Integer couponType;

    @NotNull(message = "couponImg参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "couponImg参数不能超出2000个字符", groups = {create.class, update.class})
    private String couponImg;

    @NotNull(message = "couponName参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "couponName参数不能超出100个字符", groups = {create.class, update.class})
    private String couponName;

    @NotNull(message = "num参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "num参数值不能少于0", groups = {create.class, update.class})
    private Long num;

    @NotNull(message = "amount参数缺失", groups = {create.class, update.class})
    private BigDecimal amount;

    @NotNull(message = "perLimit参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "perLimit参数值不能少于0", groups = {create.class, update.class})
    private Long perLimit;

    @NotNull(message = "minPoint参数缺失", groups = {create.class, update.class})
    private BigDecimal minPoint;

    @NotNull(message = "startTime参数缺失", groups = {create.class, update.class})
    private Date startTime;

    @NotNull(message = "endTime参数缺失", groups = {create.class, update.class})
    private Date endTime;

    @NotNull(message = "useType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "useType参数值不能少于0", groups = {create.class, update.class})
    private Integer useType;

    @NotNull(message = "note参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "note参数不能超出200个字符", groups = {create.class, update.class})
    private String note;

    @NotNull(message = "publishCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "publishCount参数值不能少于0", groups = {create.class, update.class})
    private Long publishCount;

    @NotNull(message = "useCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "useCount参数值不能少于0", groups = {create.class, update.class})
    private Long useCount;

    @NotNull(message = "receiveCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "receiveCount参数值不能少于0", groups = {create.class, update.class})
    private Long receiveCount;

    @NotNull(message = "enableStartTime参数缺失", groups = {create.class, update.class})
    private Date enableStartTime;

    @NotNull(message = "enableEndTime参数缺失", groups = {create.class, update.class})
    private Date enableEndTime;

    @NotNull(message = "code参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "code参数不能超出64个字符", groups = {create.class, update.class})
    private String code;

    @NotNull(message = "memberLevel参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberLevel参数值不能少于0", groups = {create.class, update.class})
    private Integer memberLevel;

    @NotNull(message = "publish参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "publish参数值不能少于0", groups = {create.class, update.class})
    private Integer publish;

}
