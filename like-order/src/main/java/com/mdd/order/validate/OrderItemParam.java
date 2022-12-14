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
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * 订单项信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderItemParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "orderId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderId参数值不能少于0", groups = {create.class, update.class})
    private Long orderId;
    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "orderSn参数不能超出32个字符", groups = {create.class, update.class})
    private String orderSn;
    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;
    @NotNull(message = "spuName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "spuName参数不能超出255个字符", groups = {create.class, update.class})
    private String spuName;
    @NotNull(message = "spuPic参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "spuPic参数不能超出500个字符", groups = {create.class, update.class})
    private String spuPic;
    @NotNull(message = "spuBrand参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "spuBrand参数不能超出200个字符", groups = {create.class, update.class})
    private String spuBrand;
    @NotNull(message = "categoryId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "categoryId参数值不能少于0", groups = {create.class, update.class})
    private Long categoryId;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "skuName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "skuName参数不能超出255个字符", groups = {create.class, update.class})
    private String skuName;
    @NotNull(message = "skuPic参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "skuPic参数不能超出500个字符", groups = {create.class, update.class})
    private String skuPic;
    @NotNull(message = "skuPrice参数缺失", groups = {create.class, update.class})
    private BigDecimal skuPrice;
    @NotNull(message = "skuQuantity参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuQuantity参数值不能少于0", groups = {create.class, update.class})
    private Integer skuQuantity;
    @NotNull(message = "skuAttrsVals参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "skuAttrsVals参数不能超出500个字符", groups = {create.class, update.class})
    private String skuAttrsVals;
    @NotNull(message = "promotionAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal promotionAmount;
    @NotNull(message = "couponAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal couponAmount;
    @NotNull(message = "integrationAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal integrationAmount;
    @NotNull(message = "realAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal realAmount;
    @NotNull(message = "giftIntegration参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "giftIntegration参数值不能少于0", groups = {create.class, update.class})
    private Integer giftIntegration;
    @NotNull(message = "giftGrowth参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "giftGrowth参数值不能少于0", groups = {create.class, update.class})
    private Integer giftGrowth;
}
