package com.mdd.common.to;

import lombok.Data;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-17 16:59
 **/
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}
