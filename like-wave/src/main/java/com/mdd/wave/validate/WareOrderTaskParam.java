package com.mdd.wave.validate;

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
 * 库存工作单参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WareOrderTaskParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "orderId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderId参数值不能少于0", groups = {create.class, update.class})
    private Long orderId;
    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "orderSn参数不能超出255个字符", groups = {create.class, update.class})
    private String orderSn;
    @NotNull(message = "consignee参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "consignee参数不能超出100个字符", groups = {create.class, update.class})
    private String consignee;
    @NotNull(message = "consigneeTel参数缺失", groups = {create.class, update.class})
    @Length(max = 15, message = "consigneeTel参数不能超出15个字符", groups = {create.class, update.class})
    private String consigneeTel;
    @NotNull(message = "deliveryAddress参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "deliveryAddress参数不能超出500个字符", groups = {create.class, update.class})
    private String deliveryAddress;
    @NotNull(message = "orderComment参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "orderComment参数不能超出200个字符", groups = {create.class, update.class})
    private String orderComment;
    @NotNull(message = "paymentWay参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "paymentWay参数值不能少于0", groups = {create.class, update.class})
    private Integer paymentWay;
    @NotNull(message = "taskStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "taskStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer taskStatus;
    @NotNull(message = "orderBody参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "orderBody参数不能超出255个字符", groups = {create.class, update.class})
    private String orderBody;
    @NotNull(message = "trackingNo参数缺失", groups = {create.class, update.class})
    @Length(max = 30, message = "trackingNo参数不能超出30个字符", groups = {create.class, update.class})
    private String trackingNo;
    @NotNull(message = "wareId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "wareId参数值不能少于0", groups = {create.class, update.class})
    private Long wareId;
    @NotNull(message = "taskComment参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "taskComment参数不能超出500个字符", groups = {create.class, update.class})
    private String taskComment;
}
