package com.mdd.wave.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * PurchaseDetailVo
 */
@Data
public class PurchaseDetailDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 
    private Long purchaseId;  // 采购单id
    private Long skuId;  // 采购商品id
    private Integer skuNum;  // 采购数量
    private BigDecimal skuPrice;  // 采购金额
    private Long wareId;  // 仓库id
    private Integer status;  // 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]

}
