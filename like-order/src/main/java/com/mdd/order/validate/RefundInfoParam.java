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

/**
 * 退款信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RefundInfoParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "orderReturnId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderReturnId参数值不能少于0", groups = {create.class, update.class})
    private Long orderReturnId;
    @NotNull(message = "refund参数缺失", groups = {create.class, update.class})
    private BigDecimal refund;
    @NotNull(message = "refundSn参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "refundSn参数不能超出64个字符", groups = {create.class, update.class})
    private String refundSn;
    @NotNull(message = "refundStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "refundStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer refundStatus;
    @NotNull(message = "refundChannel参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "refundChannel参数值不能少于0", groups = {create.class, update.class})
    private Integer refundChannel;
    @NotNull(message = "refundContent参数缺失", groups = {create.class, update.class})
    @Length(max = 5000, message = "refundContent参数不能超出5000个字符", groups = {create.class, update.class})
    private String refundContent;
}
