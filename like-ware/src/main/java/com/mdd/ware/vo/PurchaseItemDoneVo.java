package com.mdd.ware.vo;

import lombok.Data;

/**
 * @program: server
 * @description: 采购项完成情况
 * @author: Claire
 * @create: 2022-11-16 16:21
 **/
@Data
public class PurchaseItemDoneVo {
    //{itemId:1,status:4,reason:""}
    private Long itemId;
    private Integer status;
    private String reason;
}
