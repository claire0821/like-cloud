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

/**
 * 商品会员价格参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberPriceParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "memberLevelId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberLevelId参数值不能少于0", groups = {create.class, update.class})
    private Long memberLevelId;
    @NotNull(message = "memberLevelName参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "memberLevelName参数不能超出100个字符", groups = {create.class, update.class})
    private String memberLevelName;
    @NotNull(message = "memberPrice参数缺失", groups = {create.class, update.class})
    private BigDecimal memberPrice;
    @NotNull(message = "addOther参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "addOther参数值不能少于0", groups = {create.class, update.class})
    private Integer addOther;
}
