package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 优惠券与产品关联参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsCouponSpuRelationParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "couponId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponId参数值不能少于0", groups = {create.class, update.class})
    private Long couponId;

    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;

    @NotNull(message = "spuName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "spuName参数不能超出255个字符", groups = {create.class, update.class})
    private String spuName;

}
