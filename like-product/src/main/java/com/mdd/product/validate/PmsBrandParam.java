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
 * 品牌参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsBrandParam extends BaseParam {

    @IDMust(message = "brandId参数必传且需大于0", groups = {update.class, delete.class})
    private Long brandId;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 50, message = "name参数不能超出50个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "logo参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "logo参数不能超出2000个字符", groups = {create.class, update.class})
    private String logo;

    @NotNull(message = "descript参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "descript参数不能超出个字符", groups = {create.class, update.class})
    private String descript;

    @NotNull(message = "showStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "showStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer showStatus;

    @NotNull(message = "firstLetter参数缺失", groups = {create.class, update.class})
    @Length(max = 1, message = "firstLetter参数不能超出1个字符", groups = {create.class, update.class})
    private String firstLetter;

    @NotNull(message = "sort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sort参数值不能少于0", groups = {create.class, update.class})
    private Integer sort;

}
