package com.mdd.product.validate;

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
 * spu信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpuInfoParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "spuName参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "spuName参数不能超出200个字符", groups = {create.class, update.class})
    private String spuName;
    @NotNull(message = "spuDescription参数缺失", groups = {create.class, update.class})
    @Length(max = 1000, message = "spuDescription参数不能超出1000个字符", groups = {create.class, update.class})
    private String spuDescription;
    @NotNull(message = "catalogId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "catalogId参数值不能少于0", groups = {create.class, update.class})
    private Long catalogId;
    @NotNull(message = "brandId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "brandId参数值不能少于0", groups = {create.class, update.class})
    private Long brandId;
    @NotNull(message = "weight参数缺失", groups = {create.class, update.class})
    private BigDecimal weight;
    @NotNull(message = "publishStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "publishStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer publishStatus;
}
