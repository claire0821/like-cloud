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
import java.util.Date;

/**
 * 成长值变化历史记录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GrowthChangeHistoryParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;
    @NotNull(message = "changeCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "changeCount参数值不能少于0", groups = {create.class, update.class})
    private Integer changeCount;
    @NotNull(message = "note参数缺失", groups = {create.class, update.class})
    @Length(max = 0, message = "note参数不能超出0个字符", groups = {create.class, update.class})
    private String note;
    @NotNull(message = "sourceType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sourceType参数值不能少于0", groups = {create.class, update.class})
    private Integer sourceType;
}
