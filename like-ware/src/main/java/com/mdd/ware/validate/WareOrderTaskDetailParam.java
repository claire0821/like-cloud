package com.mdd.ware.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDLongMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 库存工作单参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WareOrderTaskDetailParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "skuName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "skuName参数不能超出255个字符", groups = {create.class, update.class})
    private String skuName;
    @NotNull(message = "skuNum参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuNum参数值不能少于0", groups = {create.class, update.class})
    private Integer skuNum;
    @NotNull(message = "taskId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "taskId参数值不能少于0", groups = {create.class, update.class})
    private Long taskId;
    @NotNull(message = "wareId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "wareId参数值不能少于0", groups = {create.class, update.class})
    private Long wareId;
    @NotNull(message = "lockStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "lockStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer lockStatus;
}
