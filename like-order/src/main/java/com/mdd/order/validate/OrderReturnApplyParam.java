package com.mdd.order.validate;

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
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderReturnApplyParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "orderId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "orderId参数值不能少于0", groups = {create.class, update.class})
    private Long orderId;
    @NotNull(message = "skuId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuId参数值不能少于0", groups = {create.class, update.class})
    private Long skuId;
    @NotNull(message = "orderSn参数缺失", groups = {create.class, update.class})
    @Length(max = 32, message = "orderSn参数不能超出32个字符", groups = {create.class, update.class})
    private String orderSn;
    @NotNull(message = "memberUsername参数缺失", groups = {create.class, update.class})
    @Length(max = 64, message = "memberUsername参数不能超出64个字符", groups = {create.class, update.class})
    private String memberUsername;
    @NotNull(message = "returnAmount参数缺失", groups = {create.class, update.class})
    private BigDecimal returnAmount;
    @NotNull(message = "returnName参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "returnName参数不能超出100个字符", groups = {create.class, update.class})
    private String returnName;
    @NotNull(message = "returnPhone参数缺失", groups = {create.class, update.class})
    @Length(max = 20, message = "returnPhone参数不能超出20个字符", groups = {create.class, update.class})
    private String returnPhone;
    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "status参数值不能少于0", groups = {create.class, update.class})
    private Integer status;
    @NotNull(message = "handleTime参数缺失", groups = {create.class, update.class})
    private Date handleTime;
    @NotNull(message = "skuImg参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "skuImg参数不能超出500个字符", groups = {create.class, update.class})
    private String skuImg;
    @NotNull(message = "skuName参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "skuName参数不能超出200个字符", groups = {create.class, update.class})
    private String skuName;
    @NotNull(message = "skuBrand参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "skuBrand参数不能超出200个字符", groups = {create.class, update.class})
    private String skuBrand;
    @NotNull(message = "skuAttrsVals参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "skuAttrsVals参数不能超出500个字符", groups = {create.class, update.class})
    private String skuAttrsVals;
    @NotNull(message = "skuCount参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "skuCount参数值不能少于0", groups = {create.class, update.class})
    private Integer skuCount;
    @NotNull(message = "skuPrice参数缺失", groups = {create.class, update.class})
    private BigDecimal skuPrice;
    @NotNull(message = "skuRealPrice参数缺失", groups = {create.class, update.class})
    private BigDecimal skuRealPrice;
    @NotNull(message = "reason参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "reason参数不能超出200个字符", groups = {create.class, update.class})
    private String reason;
    @NotNull(message = "description述参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "description述参数不能超出500个字符", groups = {create.class, update.class})
    private String description述;
    @NotNull(message = "descPics参数缺失", groups = {create.class, update.class})
    @Length(max = 2000, message = "descPics参数不能超出2000个字符", groups = {create.class, update.class})
    private String descPics;
    @NotNull(message = "handleNote参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "handleNote参数不能超出500个字符", groups = {create.class, update.class})
    private String handleNote;
    @NotNull(message = "handleMan参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "handleMan参数不能超出200个字符", groups = {create.class, update.class})
    private String handleMan;
    @NotNull(message = "receiveMan参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "receiveMan参数不能超出100个字符", groups = {create.class, update.class})
    private String receiveMan;
    @NotNull(message = "receiveTime参数缺失", groups = {create.class, update.class})
    private Date receiveTime;
    @NotNull(message = "receiveNote参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "receiveNote参数不能超出500个字符", groups = {create.class, update.class})
    private String receiveNote;
    @NotNull(message = "receivePhone参数缺失", groups = {create.class, update.class})
    @Length(max = 20, message = "receivePhone参数不能超出20个字符", groups = {create.class, update.class})
    private String receivePhone;
    @NotNull(message = "companyAddress参数缺失", groups = {create.class, update.class})
    @Length(max = 500, message = "companyAddress参数不能超出500个字符", groups = {create.class, update.class})
    private String companyAddress;
}
