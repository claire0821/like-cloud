package com.mdd.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: server
 * @description: 采购商品合并到采购单
 * @author: Claire
 * @create: 2022-11-16 15:46
 **/
@Data
public class MergeVo {
    private Long purchaseId; //整单id
    private List<Long> items;//[1,2,3,4] //合并项集合
}
