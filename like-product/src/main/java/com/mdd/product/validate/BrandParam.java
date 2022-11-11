package com.mdd.product.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BrandParam extends BaseParam {

    @IDLongMust(message = "brandId参数必传且需大于等于0", groups = {update.class, delete.class,change.class})
    private Long brandId;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 50, message = "name参数不能超出50个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "logo参数缺失", groups = {create.class, update.class})
    @URL(message = "logo必须是一个合法的url地址",groups={create.class,update.class})
    @Length(max = 2000, message = "logo参数不能超出2000个字符", groups = {create.class, update.class})
    private String logo;

    @NotNull(message = "descript参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "descript参数不能超出个字符", groups = {create.class, update.class})
    private String descript;

    //TODO 生成代码增加int合法值
    @NotNull(message = "showStatus参数缺失", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, message = "showStatus不是合法值", groups = {create.class, update.class})
    private Integer showStatus;

    //TODO 根据字段是否允许为空
//    @NotNull(message = "firstLetter参数缺失", groups = {create.class, update.class})
//    @Length(max = 1, message = "firstLetter参数不能超出1个字符", groups = {create.class, update.class})
//    @NotEmpty(groups={create.class})
    @Pattern(regexp="^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups={create.class,update.class})
    private String firstLetter;

    @NotNull(message = "sort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sort参数值不能少于0", groups = {create.class, update.class})
    private Integer sort;

}