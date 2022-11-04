package com.mdd.product.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * spu图片参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsSpuImagesParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;

    @NotNull(message = "imgName参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "imgName参数不能超出200个字符", groups = {create.class, update.class})
    private String imgName;

    @NotNull(message = "imgUrl参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "imgUrl参数不能超出255个字符", groups = {create.class, update.class})
    private String imgUrl;

    @NotNull(message = "imgSort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "imgSort参数值不能少于0", groups = {create.class, update.class})
    private Long imgSort;

    @NotNull(message = "defaultImg参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "defaultImg参数值不能少于0", groups = {create.class, update.class})
    private Long defaultImg;

}
