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

/**
 * 优惠券分类关联参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CouponSpuCategoryRelationParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "couponId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponId参数值不能少于0", groups = {create.class, update.class})
    private Long couponId;
    @NotNull(message = "categoryId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "categoryId参数值不能少于0", groups = {create.class, update.class})
    private Long categoryId;
    @NotNull(message = "categoryName参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "categoryName参数不能超出64个字符", groups = {create.class, update.class})
    private String categoryName;
}
