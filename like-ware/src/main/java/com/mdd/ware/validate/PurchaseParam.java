package com.mdd.ware.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDLongMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 采购信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "assigneeId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "assigneeId参数值不能少于0", groups = {create.class, update.class})
    private Long assigneeId;
    @NotNull(message = "assigneeName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "assigneeName参数不能超出255个字符", groups = {create.class, update.class})
    private String assigneeName;
    @NotNull(message = "phone参数缺失", groups = {create.class, update.class})
    @Length(max = 13, message = "phone参数不能超出13个字符", groups = {create.class, update.class})
    private String phone;
    @NotNull(message = "priority参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priority参数值不能少于0", groups = {create.class, update.class})
    private Integer priority;
    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;
    @NotNull(message = "wareId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "wareId参数值不能少于0", groups = {create.class, update.class})
    private Long wareId;
    @NotNull(message = "amount参数缺失", groups = {create.class, update.class})
    private BigDecimal amount;
}
