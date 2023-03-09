package com.mdd.coupon.validate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * 优惠券信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CouponParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "code参数缺失", groups = { update.class})
    @Length(max = 64, message = "code参数不能超出64个字符", groups = {create.class, update.class})
    private String code;
    @Length(max = 2000, message = "couponImg参数不能超出2000个字符", groups = {create.class,update.class})
    private String couponImg;
    @NotNull(message = "couponName参数缺失", groups = {create.class})
    @Length(max = 100, message = "couponName参数不能超出100个字符", groups = {create.class, update.class})
    private String couponName;
    @NotNull(message = "amount参数缺失", groups = {create.class})
    private BigDecimal amount;
    @NotNull(message = "publishCountType参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "publishCount参数值不能少于0", groups = {create.class, update.class})
    private Integer publishCountType;
    @DecimalMin(value = "0", message = "publishCount参数值不能少于0", groups = {create.class, update.class})
    private Integer publishCount;
    @NotNull(message = "useCount参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "useCount参数值不能少于0", groups = {create.class,update.class})
    private Integer useCount;
    @NotNull(message = "receiveCount参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "receiveCount参数值不能少于0", groups = {create.class,update.class})
    private Integer receiveCount;
    @NotNull(message = "publishCountType参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "conditionType参数值不能少于0", groups = {create.class, update.class})
    private Integer conditionType;
    @DecimalMin(value = "0", message = "conditionMoney参数值不能少于0", groups = {create.class, update.class})
    private BigDecimal conditionMoney;
    @NotNull(message = "useTimeType参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "useTimeType参数值不能少于0", groups = {create.class, update.class})
    private Integer useTimeType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useTimeEnd;
    @DecimalMin(value = "0", message = "useTime参数值不能少于0", groups = {create.class, update.class})
    private Integer useTime;
    @NotNull(message = "getType参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "getType参数值不能少于0", groups = {create.class, update.class})
    private Integer getType;
    @NotNull(message = "getCountType参数缺失", groups = {create.class})
    @DecimalMin(value = "0", message = "getCountType参数值不能少于0", groups = {create.class, update.class})
    private Integer getCountType;
    @DecimalMin(value = "0", message = "getCount参数值不能少于0", groups = {create.class, update.class})
    private Integer getCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "enableStartTime参数缺失", groups = {create.class, update.class})
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enableStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "enableEndTime参数缺失", groups = {create.class, update.class})
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enableEndTime;
    @NotNull(message = "useType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "useType参数值不能少于0", groups = {create.class, update.class})
    private Integer useType;
    @NotNull(message = "memberLevel参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberLevel参数值不能少于0", groups = {create.class, update.class})
    private Integer memberLevel;
    @Length(max = 200, message = "note参数不能超出200个字符", groups = { create.class,update.class})
    private String note;
    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;
}
