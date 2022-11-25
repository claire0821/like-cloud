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

/**
 * 会员收藏的专题活动参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberCollectSubjectParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "subjectId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "subjectId参数值不能少于0", groups = {create.class, update.class})
    private Long subjectId;
    @NotNull(message = "subjectName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "subjectName参数不能超出255个字符", groups = {create.class, update.class})
    private String subjectName;
    @NotNull(message = "subjectImg参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "subjectImg参数不能超出500个字符", groups = {create.class, update.class})
    private String subjectImg;
    @NotNull(message = "subjectUrll参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "subjectUrll参数不能超出500个字符", groups = {create.class, update.class})
    private String subjectUrll;
}
