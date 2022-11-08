package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsHomeSubjectParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "name参数不能超出200个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "title参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "title参数不能超出255个字符", groups = {create.class, update.class})
    private String title;

    @NotNull(message = "subTitle参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "subTitle参数不能超出255个字符", groups = {create.class, update.class})
    private String subTitle;

    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;

    @NotNull(message = "url参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "url参数不能超出500个字符", groups = {create.class, update.class})
    private String url;

    @NotNull(message = "sort参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sort参数值不能少于0", groups = {create.class, update.class})
    private Long sort;

    @NotNull(message = "img参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "img参数不能超出500个字符", groups = {create.class, update.class})
    private String img;

}
