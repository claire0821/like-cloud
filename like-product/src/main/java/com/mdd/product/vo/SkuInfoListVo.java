package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SkuInfoVo
 */
@Data
public class SkuInfoListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long skuId;  // skuId
    private Long spuId;  // spuId
    private String skuName;  // sku名称
    private String skuDesc;  // sku介绍描述
    private Long catalogId;  // 所属分类id
    private Long brandId;  // 品牌id
    private String skuDefaultImg;  // 默认图片
    private String skuTitle;  // 标题
    private String skuSubtitle;  // 副标题
    private BigDecimal price;  // 价格
    private Long saleCount;  // 销量

}
