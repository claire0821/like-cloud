package com.mdd.member.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 会员收藏的商品参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsMemberCollectSpuParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;

    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;

    @NotNull(message = "spuName参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "spuName参数不能超出500个字符", groups = {create.class, update.class})
    private String spuName;

    @NotNull(message = "spuImg参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "spuImg参数不能超出500个字符", groups = {create.class, update.class})
    private String spuImg;

}
