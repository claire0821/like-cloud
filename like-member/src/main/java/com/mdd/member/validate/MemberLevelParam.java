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

/**
 * 会员等级参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberLevelParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "name参数不能超出100个字符", groups = {create.class, update.class})
    private String name;
    @NotNull(message = "growthPoint参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "growthPoint参数值不能少于0", groups = {create.class, update.class})
    private Integer growthPoint;
    @NotNull(message = "defaultStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "defaultStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer defaultStatus;
    @NotNull(message = "freeFreightPoint参数缺失", groups = {create.class, update.class})
    private BigDecimal freeFreightPoint;
    @NotNull(message = "commentGrowthPoint参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentGrowthPoint参数值不能少于0", groups = {create.class, update.class})
    private Integer commentGrowthPoint;
    @NotNull(message = "priviledgeFreeFreight参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priviledgeFreeFreight参数值不能少于0", groups = {create.class, update.class})
    private Integer priviledgeFreeFreight;
    @NotNull(message = "priviledgeMemberPrice参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priviledgeMemberPrice参数值不能少于0", groups = {create.class, update.class})
    private Integer priviledgeMemberPrice;
    @NotNull(message = "priviledgeBirthday参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priviledgeBirthday参数值不能少于0", groups = {create.class, update.class})
    private Integer priviledgeBirthday;
    @NotNull(message = "note参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "note参数不能超出255个字符", groups = {create.class, update.class})
    private String note;
    private String image;//等级图标
    private String backgroundImage;//背景图片
}
