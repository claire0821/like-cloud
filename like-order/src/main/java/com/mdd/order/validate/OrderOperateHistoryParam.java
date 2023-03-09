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

/**
 * 订单操作历史记录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderOperateHistoryParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "orderSn参数不能超出32个字符", groups = {create.class, update.class})
    private String orderSn;
    @NotNull(message = "operateManType参数缺失", groups = {create.class, update.class})
    private Integer operateManType;
    private Long operateManId;
    @NotNull(message = "orderStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer orderStatus;
    @NotNull(message = "actionType参数缺失", groups = {create.class, update.class})
    private Integer actionType;
    @Length(max = 500, message = "note参数不能超出500个字符", groups = {create.class, update.class})
    private String note;
}
