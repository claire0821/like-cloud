package com.mdd.product.validate;

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
 * 商品评价参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpuCommentParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "spuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "spuId参数值不能少于0", groups = {create.class, update.class})
    private Long spuId;
    @NotNull(message = "spuName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "spuName参数不能超出255个字符", groups = {create.class, update.class})
    private String spuName;
    @NotNull(message = "memberNickName参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "memberNickName参数不能超出255个字符", groups = {create.class, update.class})
    private String memberNickName;
    @NotNull(message = "star参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "star参数值不能少于0", groups = {create.class, update.class})
    private Integer star;
    @NotNull(message = "memberIp参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "memberIp参数不能超出64个字符", groups = {create.class, update.class})
    private String memberIp;
    @NotNull(message = "showStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "showStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer showStatus;
    @NotNull(message = "spuAttributes参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "spuAttributes参数不能超出255个字符", groups = {create.class, update.class})
    private String spuAttributes;
    @NotNull(message = "likesCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "likesCount参数值不能少于0", groups = {create.class, update.class})
    private Integer likesCount;
    @NotNull(message = "replyCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "replyCount参数值不能少于0", groups = {create.class, update.class})
    private Integer replyCount;
    @NotNull(message = "resources参数缺失", groups = {create.class, update.class})
    @Length(max = 1000, message = "resources参数不能超出1000个字符", groups = {create.class, update.class})
    private String resources;
    private String content;
    @NotNull(message = "memberIcon参数缺失", groups = {create.class, update.class})
    @Length(max = 255, message = "memberIcon参数不能超出255个字符", groups = {create.class, update.class})
    private String memberIcon;
    @NotNull(message = "commentType参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "commentType参数值不能少于0", groups = {create.class, update.class})
    private Integer commentType;
}
