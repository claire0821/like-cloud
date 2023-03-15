package com.mdd.admin.validate;

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
import java.util.Date;

/**
 * 导航链接参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LinkParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于0", groups = {update.class, change.class})
    private Long id;
    @NotNull(message = "linkType参数缺失", groups = {create.class, update.class})
    @Length(max = 50, message = "linkType参数不能超出50个字符", groups = {create.class, update.class})
    private String type;
    @NotNull(message = "linkName参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "linkName参数不能超出100个字符", groups = {create.class, update.class})
    private String name;
    @NotNull(message = "linkPath参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "linkPath参数不能超出100个字符", groups = {create.class, update.class})
    private String path;
    @NotNull(message = "linkIcon参数缺失", groups = {update.class})
    @Length(max = 100, message = "linkIcon参数不能超出100个字符", groups = {create.class, update.class})
    private String image;
}
