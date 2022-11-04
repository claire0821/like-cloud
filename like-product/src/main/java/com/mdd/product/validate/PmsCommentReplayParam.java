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
 * 商品评价回复关系参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsCommentReplayParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "commentId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentId参数值不能少于0", groups = {create.class, update.class})
    private Long commentId;

    @NotNull(message = "replyId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "replyId参数值不能少于0", groups = {create.class, update.class})
    private Long replyId;

}
