package com.mdd.ware.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * PurchaseVo
 */
@Data
public class PurchaseDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 
    private Long assigneeId;  // 
    private String assigneeName;  // 
    private String phone;  // 
    private Integer priority;  // 
    private Integer status;  // 
    private Long wareId;  // 
    private BigDecimal amount;  // 

}
