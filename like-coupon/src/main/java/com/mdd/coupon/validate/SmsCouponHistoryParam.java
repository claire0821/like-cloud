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
 * 优惠券领取历史记录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsCouponHistoryParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Long id;

    @NotNull(message = "couponId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "couponId参数值不能少于0", groups = {create.class, update.class})
    private Long couponId;

    @NotNull(message = "memberId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "memberId参数值不能少于0", groups = {create.class, update.class})
    private Long memberId;

    @NotNull(message = "memberNickName参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "memberNickName参数不能超出64个字符", groups = {create.class, update.class})
    private String memberNickName;

    @NotNull(message = "getType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "getType参数值不能少于0", groups = {create.class, update.class})
    private Integer getType;

    @NotNull(message = "useType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "useType参数值不能少于0", groups = {create.class, update.class})
    private Integer useType;

    @NotNull(message = "useTime参数缺失", groups = {create.class, update.class})
    private Date useTime;

    @NotNull(message = "orderId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderId参数值不能少于0", groups = {create.class, update.class})
    private Long orderId;

    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderSn参数值不能少于0", groups = {create.class, update.class})
    private Long orderSn;

}
