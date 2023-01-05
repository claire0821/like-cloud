package com.mdd.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-20 17:35
 **/
@Data
public class ProductDetaliSpuVo {
    private Long spuId; // skuId
    private String spuName;  // 商品名称
    private String spuDescription;  // 商品描述
    private Long catelogId;  // 所属分类id
    private String catelogName;  // 所属分类
    private Long brandId;  // 品牌id
    private String brandName;  // 品牌
    private BigDecimal weight;
    private Integer publishStatus;  // 上架状态[0 - 下架，1 - 上架]
}
