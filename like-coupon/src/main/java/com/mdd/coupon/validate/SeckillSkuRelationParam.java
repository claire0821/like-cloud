package com.mdd.coupon.validate;

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
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * 秒杀活动商品关联参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SeckillSkuRelationParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
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
    private BigDecimal seckillPrice;
    @NotNull(message = "seckillCount参数缺失", groups = {create.class, update.class})
    private BigDecimal seckillCount;
    @NotNull(message = "seckillLimit参数缺失", groups = {create.class, update.class})
    private BigDecimal seckillLimit;
    @NotNull(message = "seckillSort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "seckillSort参数值不能少于0", groups = {create.class, update.class})
    private Integer seckillSort;
}
