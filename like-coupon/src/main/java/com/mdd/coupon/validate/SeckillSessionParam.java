package com.mdd.coupon.validate;

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
import java.util.Date;

/**
 * 秒杀活动场次参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SeckillSessionParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "name参数不能超出200个字符", groups = {create.class, update.class})
    private String name;
    @NotNull(message = "startTime参数缺失", groups = {create.class, update.class})
    private Date startTime;
    @NotNull(message = "endTime参数缺失", groups = {create.class, update.class})
    private Date endTime;
    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;
}
