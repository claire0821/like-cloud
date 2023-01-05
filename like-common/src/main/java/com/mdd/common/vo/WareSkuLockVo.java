package com.mdd.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: server
 * @description: 锁定库存的vo
 * @author: Claire
 * @create: 2022-12-22 16:35
 **/
@Data
public class WareSkuLockVo {

    private String orderSn;

    /** 需要锁住的所有库存信息 **/
    private List<CartItemVo> locks;
}
