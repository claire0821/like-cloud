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

/**
 * spu信息介绍参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpuInfoDescParam extends BaseParam {

    @IDLongMust(message = "spuId参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long spuId;
    @NotNull(message = "decript参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "decript参数不能超出个字符", groups = {create.class, update.class})
    private String decript;
}
