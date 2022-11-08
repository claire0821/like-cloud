package com.mdd.member.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
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
public class UmsMemberLevelParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "name参数不能超出100个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "growthPoint参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "growthPoint参数值不能少于0", groups = {create.class, update.class})
    private Long growthPoint;

    @NotNull(message = "defaultStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "defaultStatus参数值不能少于0", groups = {create.class, update.class})
    private Long defaultStatus;

    @NotNull(message = "freeFreightPoint参数缺失", groups = {create.class, update.class})
    private BigDecimal freeFreightPoint;

    @NotNull(message = "commentGrowthPoint参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentGrowthPoint参数值不能少于0", groups = {create.class, update.class})
    private Long commentGrowthPoint;

    @NotNull(message = "priviledgeFreeFreight参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priviledgeFreeFreight参数值不能少于0", groups = {create.class, update.class})
    private Long priviledgeFreeFreight;

    @NotNull(message = "priviledgeMemberPrice参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priviledgeMemberPrice参数值不能少于0", groups = {create.class, update.class})
    private Long priviledgeMemberPrice;

    @NotNull(message = "priviledgeBirthday参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "priviledgeBirthday参数值不能少于0", groups = {create.class, update.class})
    private Long priviledgeBirthday;

    @NotNull(message = "note参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "note参数不能超出255个字符", groups = {create.class, update.class})
    private String note;

}
