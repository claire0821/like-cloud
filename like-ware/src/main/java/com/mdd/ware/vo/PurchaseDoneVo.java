package com.mdd.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: server
 * @description: 采购单完成情况
 * @author: Claire
 * @create: 2022-11-16 16:20
 **/
@Data
public class PurchaseDoneVo {

    @NotNull
    private Long id;//采购单id
    private List<PurchaseItemDoneVo> items;
}
