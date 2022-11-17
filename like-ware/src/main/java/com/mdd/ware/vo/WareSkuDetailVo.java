package com.mdd.ware.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * WareSkuVo
 */
@Data
public class WareSkuDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private Long wareId;  // 仓库id
    private Integer stock;  // 库存数
    private String skuName;  // sku_name
    private Integer stockLocked;  // 锁定库存

}
