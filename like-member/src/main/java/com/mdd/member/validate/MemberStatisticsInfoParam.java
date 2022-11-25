package com.mdd.member.validate;

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

/**
 * 会员统计信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberStatisticsInfoParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
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
    private Integer orderCount;
    @NotNull(message = "couponCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponCount参数值不能少于0", groups = {create.class, update.class})
    private Integer couponCount;
    @NotNull(message = "commentCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentCount参数值不能少于0", groups = {create.class, update.class})
    private Integer commentCount;
    @NotNull(message = "returnOrderCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "returnOrderCount参数值不能少于0", groups = {create.class, update.class})
    private Integer returnOrderCount;
    @NotNull(message = "loginCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "loginCount参数值不能少于0", groups = {create.class, update.class})
    private Integer loginCount;
    @NotNull(message = "attendCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "attendCount参数值不能少于0", groups = {create.class, update.class})
    private Integer attendCount;
    @NotNull(message = "fansCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "fansCount参数值不能少于0", groups = {create.class, update.class})
    private Integer fansCount;
    @NotNull(message = "collectProductCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "collectProductCount参数值不能少于0", groups = {create.class, update.class})
    private Integer collectProductCount;
    @NotNull(message = "collectSubjectCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "collectSubjectCount参数值不能少于0", groups = {create.class, update.class})
    private Integer collectSubjectCount;
    @NotNull(message = "collectCommentCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "collectCommentCount参数值不能少于0", groups = {create.class, update.class})
    private Integer collectCommentCount;
    @NotNull(message = "inviteFriendCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "inviteFriendCount参数值不能少于0", groups = {create.class, update.class})
    private Integer inviteFriendCount;
}
