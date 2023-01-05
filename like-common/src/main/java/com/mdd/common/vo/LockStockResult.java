package com.mdd.common.vo;

import lombok.Data;

/**
 * @program: server
 * @description: 库存锁定结果
 * @author: Claire
 * @create: 2022-12-22 16:30
 **/
@Data
public class LockStockResult {
    private Long skuId;

    private Integer num;

    /** 是否锁定成功 **/
    private Boolean locked;
}
