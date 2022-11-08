package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 秒杀活动商品关联参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsSeckillSkuRelationParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "promotionId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "promotionId参数值不能少于0", groups = {create.class, update.class})
    private Long promotionId;

    @NotNull(message = "promotionSessionId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "promotionSessionId参数值不能少于0", groups = {create.class, update.class})
    private Long promotionSessionId;

    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;

    @NotNull(message = "seckillPrice参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "seckillPrice参数值不能少于0", groups = {create.class, update.class})
    private Long seckillPrice;

    @NotNull(message = "seckillCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "seckillCount参数值不能少于0", groups = {create.class, update.class})
    private Long seckillCount;

    @NotNull(message = "seckillLimit参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "seckillLimit参数值不能少于0", groups = {create.class, update.class})
    private Long seckillLimit;

    @NotNull(message = "seckillSort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "seckillSort参数值不能少于0", groups = {create.class, update.class})
    private Long seckillSort;

}
