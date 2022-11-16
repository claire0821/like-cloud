package com.mdd.wave.validate;

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
 * 【请填写功能名称】参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UndoLogParam extends BaseParam {

    @IDLongMust(message = "id参数必传且需大于等于0", groups = {update.class, delete.class, change.class})
    private Long id;
    @NotNull(message = "branchId参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "branchId参数值不能少于0", groups = {create.class, update.class})
    private Long branchId;
    @NotNull(message = "xid参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "xid参数不能超出100个字符", groups = {create.class, update.class})
    private String xid;
    @NotNull(message = "context参数缺失", groups = {create.class, update.class})
    @Length(max = 128, message = "context参数不能超出128个字符", groups = {create.class, update.class})
    private String context;
    @NotNull(message = "rollbackInfo参数缺失", groups = {create.class, update.class})
    @Length(max = 5000, message = "rollbackInfo参数不能超出个字符", groups = {create.class, update.class})
    private String rollbackInfo;
    @NotNull(message = "logStatus参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "logStatus参数值不能少于0", groups = {create.class, update.class})
    private Integer logStatus;
    @NotNull(message = "logCreated参数缺失", groups = {create.class, update.class})
    private Date logCreated;
    @NotNull(message = "logModified参数缺失", groups = {create.class, update.class})
    private Date logModified;
    @NotNull(message = "ext参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "ext参数不能超出100个字符", groups = {create.class, update.class})
    private String ext;
}
