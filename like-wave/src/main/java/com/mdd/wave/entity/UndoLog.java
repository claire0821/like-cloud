package com.mdd.wave.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * 【请填写功能名称】实体
 */
@Data
public class UndoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // 
    private Long branchId; // 
    private String xid; // 
    private String context; // 
    private String rollbackInfo; // 
    private Integer logStatus; // 
    private Date logCreated; // 
    private Date logModified; // 
    private String ext; // 

}