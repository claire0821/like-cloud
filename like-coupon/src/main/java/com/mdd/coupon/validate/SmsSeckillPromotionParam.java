package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * 秒杀活动参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsSeckillPromotionParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "title参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "title参数不能超出255个字符", groups = {create.class, update.class})
    private String title;

    @NotNull(message = "startTime参数缺失", groups = {create.class, update.class})
    private Date startTime;

    @NotNull(message = "endTime参数缺失", groups = {create.class, update.class})
    private Date endTime;

    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Long status;

    @NotNull(message = "userId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "userId参数值不能少于0", groups = {create.class, update.class})
    private Long userId;

}
