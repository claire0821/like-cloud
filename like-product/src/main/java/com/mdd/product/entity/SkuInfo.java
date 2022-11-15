package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * sku信息实体
 */
@Data
public class SkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="sku_id", type= IdType.AUTO)
    private Long skuId; // skuId
    private Long spuId; // spuId
    private String skuName; // sku名称
    private String skuDesc; // sku介绍描述
    private Long catalogId; // 所属分类id
    private Long brandId; // 品牌id
    private String skuDefaultImg; // 默认图片
    private String skuTitle; // 标题
    private String skuSubtitle; // 副标题
    private BigDecimal price; // 价格
    private Long saleCount; // 销量

}