package com.mdd.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购信息实体
 */
@Data
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // 
    private Long assigneeId; // 
    private String assigneeName; // 
    private String phone; // 
    private Integer priority; // 
    private Integer status; // 
    private Long wareId; // 
    private BigDecimal amount; // 
    private Date createTime; // 
    private Date updateTime; // 

}