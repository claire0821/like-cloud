package com.mdd.ware.vo;

import lombok.Data;

/**
 * @program: server
 * @description: 是否有库存
 * @author: Claire
 * @create: 2022-11-17 16:26
 **/
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}
