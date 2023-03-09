package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SpuInfoVo
 */
@Data
public class SpuInfoDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 商品id
    private String spuName;  // 商品名称
    private String spuDescription;  // 商品描述
    private Long catalogId;  // 所属分类id
    private Long brandId;  // 品牌id
    private BigDecimal weight;  //
    private Integer publishStatus;  // 上架状态[0 - 下架，1 - 上架]
    private String spuImg;  // 图片地址
}
