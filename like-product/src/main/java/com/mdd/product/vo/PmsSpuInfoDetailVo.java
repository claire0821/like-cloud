package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * PmsSpuInfoVo
 */
@Data
public class PmsSpuInfoDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 商品id
    private String spuName;  // 商品名称
    private String spuDescription;  // 商品描述
    private Long catalogId;  // 所属分类id
    private Long brandId;  // 品牌id
    private BigDecimal weight;  // 
    private Long publishStatus;  // 上架状态[0 - 下架，1 - 上架]

}
