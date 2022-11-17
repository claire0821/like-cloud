package com.mdd.ware.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * UndoLogVo
 */
@Data
public class UndoLogDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 
    private Long branchId;  // 
    private String xid;  // 
    private String context;  // 
    private String rollbackInfo;  // 
    private Integer logStatus;  // 
    private Date logCreated;  // 
    private Date logModified;  // 
    private String ext;  // 

}
