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
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * 订单参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;
    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "orderSn参数不能超出32个字符", groups = {create.class, update.class})
    private String orderSn;
    @NotNull(message = "couponId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponId参数值不能少于0", groups = {create.class, update.class})
    private Long couponId;
    @NotNull(message = "memberUsername参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "memberUsername参数不能超出200个字符", groups = {create.class, update.class})
    private String memberUsername;
    @NotNull(message = "totalAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal totalAmount;
    @NotNull(message = "payAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal payAmount;
    @NotNull(message = "freightAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal freightAmount;
    @NotNull(message = "promotionAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal promotionAmount;
    @NotNull(message = "integrationAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal integrationAmount;
    @NotNull(message = "couponAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal couponAmount;
    @NotNull(message = "discountAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal discountAmount;
    @NotNull(message = "payType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "payType参数值不能少于0", groups = {create.class, update.class})
    private Integer payType;
    @NotNull(message = "sourceType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sourceType参数值不能少于0", groups = {create.class, update.class})
    private Integer sourceType;
    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;
    @NotNull(message = "deliveryCompany参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "deliveryCompany参数不能超出64个字符", groups = {create.class, update.class})
    private String deliveryCompany;
    @NotNull(message = "deliverySn参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "deliverySn参数不能超出64个字符", groups = {create.class, update.class})
    private String deliverySn;
    @NotNull(message = "autoConfirmDay参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "autoConfirmDay参数值不能少于0", groups = {create.class, update.class})
    private Integer autoConfirmDay;
    @NotNull(message = "integration参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "integration参数值不能少于0", groups = {create.class, update.class})
    private Integer integration;
    @NotNull(message = "growth参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "growth参数值不能少于0", groups = {create.class, update.class})
    private Integer growth;
    @NotNull(message = "billType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "billType参数值不能少于0", groups = {create.class, update.class})
    private Integer billType;
    @NotNull(message = "billHeader参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "billHeader参数不能超出255个字符", groups = {create.class, update.class})
    private String billHeader;
    @NotNull(message = "billContent参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "billContent参数不能超出255个字符", groups = {create.class, update.class})
    private String billContent;
    @NotNull(message = "billReceiverPhone参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "billReceiverPhone参数不能超出32个字符", groups = {create.class, update.class})
    private String billReceiverPhone;
    @NotNull(message = "billReceiverEmail参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "billReceiverEmail参数不能超出64个字符", groups = {create.class, update.class})
    private String billReceiverEmail;
    @NotNull(message = "receiverName参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "receiverName参数不能超出100个字符", groups = {create.class, update.class})
    private String receiverName;
    @NotNull(message = "receiverPhone参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "receiverPhone参数不能超出32个字符", groups = {create.class, update.class})
    private String receiverPhone;
    @NotNull(message = "receiverPostCode参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "receiverPostCode参数不能超出32个字符", groups = {create.class, update.class})
    private String receiverPostCode;
    @NotNull(message = "receiverProvince参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "receiverProvince参数不能超出32个字符", groups = {create.class, update.class})
    private String receiverProvince;
    @NotNull(message = "receiverCity参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "receiverCity参数不能超出32个字符", groups = {create.class, update.class})
    private String receiverCity;
    @NotNull(message = "receiverRegion参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "receiverRegion参数不能超出32个字符", groups = {create.class, update.class})
    private String receiverRegion;
    @NotNull(message = "receiverDetailAddress参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "receiverDetailAddress参数不能超出200个字符", groups = {create.class, update.class})
    private String receiverDetailAddress;
    @NotNull(message = "note参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "note参数不能超出500个字符", groups = {create.class, update.class})
    private String note;
    @NotNull(message = "confirmStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "confirmStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer confirmStatus;
    @NotNull(message = "deleteStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "deleteStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer deleteStatus;
    @NotNull(message = "useIntegration参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "useIntegration参数值不能少于0", groups = {create.class, update.class})
    private Integer useIntegration;
    @NotNull(message = "paymentTime参数缺失", groups = {create.class, update.class})
    private Date paymentTime;
    @NotNull(message = "deliveryTime参数缺失", groups = {create.class, update.class})
    private Date deliveryTime;
    @NotNull(message = "receiveTime参数缺失", groups = {create.class, update.class})
    private Date receiveTime;
    @NotNull(message = "commentTime参数缺失", groups = {create.class, update.class})
    private Date commentTime;
    @NotNull(message = "modifyTime参数缺失", groups = {create.class, update.class})
    private Date modifyTime;
}
