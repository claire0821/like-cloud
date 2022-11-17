package com.mdd.ware.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDLongMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 仓库信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WareInfoParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "name参数不能超出255个字符", groups = {create.class, update.class})
    private String name;
    @NotNull(message = "address参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "address参数不能超出255个字符", groups = {create.class, update.class})
    private String address;
    @NotNull(message = "areacode参数缺失", groups = {create.class, update.class})
    @Length(max = 20, message = "areacode参数不能超出20个字符", groups = {create.class, update.class})
    private String areacode;
}
