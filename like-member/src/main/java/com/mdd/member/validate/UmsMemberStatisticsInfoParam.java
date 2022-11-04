package com.mdd.member.validate;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * 会员统计信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsMemberStatisticsInfoParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;

    @NotNull(message = "consumeAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal consumeAmount;

    @NotNull(message = "couponAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal couponAmount;

    @NotNull(message = "orderCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderCount参数值不能少于0", groups = {create.class, update.class})
    private Long orderCount;

    @NotNull(message = "couponCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponCount参数值不能少于0", groups = {create.class, update.class})
    private Long couponCount;

    @NotNull(message = "commentCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentCount参数值不能少于0", groups = {create.class, update.class})
    private Long commentCount;

    @NotNull(message = "returnOrderCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "returnOrderCount参数值不能少于0", groups = {create.class, update.class})
    private Long returnOrderCount;

    @NotNull(message = "loginCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "loginCount参数值不能少于0", groups = {create.class, update.class})
    private Long loginCount;

    @NotNull(message = "attendCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attendCount参数值不能少于0", groups = {create.class, update.class})
    private Long attendCount;

    @NotNull(message = "fansCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "fansCount参数值不能少于0", groups = {create.class, update.class})
    private Long fansCount;

    @NotNull(message = "collectProductCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "collectProductCount参数值不能少于0", groups = {create.class, update.class})
    private Long collectProductCount;

    @NotNull(message = "collectSubjectCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "collectSubjectCount参数值不能少于0", groups = {create.class, update.class})
    private Long collectSubjectCount;

    @NotNull(message = "collectCommentCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "collectCommentCount参数值不能少于0", groups = {create.class, update.class})
    private Long collectCommentCount;

    @NotNull(message = "inviteFriendCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "inviteFriendCount参数值不能少于0", groups = {create.class, update.class})
    private Long inviteFriendCount;

}
