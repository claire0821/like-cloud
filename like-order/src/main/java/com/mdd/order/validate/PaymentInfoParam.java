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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * 支付信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaymentInfoParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "orderSn参数不能超出32个字符", groups = {create.class, update.class})
    private String orderSn;
    @NotNull(message = "orderId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderId参数值不能少于0", groups = {create.class, update.class})
    private Long orderId;
    @NotNull(message = "alipayTradeNo参数缺失", groups = {create.class, update.class})
    @Length(max = 50, message = "alipayTradeNo参数不能超出50个字符", groups = {create.class, update.class})
    private String alipayTradeNo;
    @NotNull(message = "totalAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal totalAmount;
    @NotNull(message = "subject参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "subject参数不能超出200个字符", groups = {create.class, update.class})
    private String subject;
    @NotNull(message = "paymentStatus参数缺失", groups = {create.class, update.class})
    @Length(max = 20, message = "paymentStatus参数不能超出20个字符", groups = {create.class, update.class})
    private String paymentStatus;
    @NotNull(message = "confirmTime参数缺失", groups = {create.class, update.class})
    private Date confirmTime;
    @NotNull(message = "callbackContent参数缺失", groups = {create.class, update.class})
    @Length(max = 4000, message = "callbackContent参数不能超出4000个字符", groups = {create.class, update.class})
    private String callbackContent;
    @NotNull(message = "callbackTime参数缺失", groups = {create.class, update.class})
    private Date callbackTime;
}
