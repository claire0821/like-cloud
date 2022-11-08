package com.mdd.coupon.validate;

import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * 秒杀商品通知订阅参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsSeckillSkuNoticeParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;

    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;

    @NotNull(message = "sessionId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "sessionId参数值不能少于0", groups = {create.class, update.class})
    private Long sessionId;

    @NotNull(message = "subcribeTime参数缺失", groups = {create.class, update.class})
    private Date subcribeTime;

    @NotNull(message = "sendTime参数缺失", groups = {create.class, update.class})
    private Date sendTime;

    @NotNull(message = "noticeType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "noticeType参数值不能少于0", groups = {create.class, update.class})
    private Integer noticeType;

}
