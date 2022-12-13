package com.mdd.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-06 13:56
 **/
@Data
public class ProductDetaliSkuVo {
    private Long skuId; // skuId
    private String name; // sku名称
    private String desc; // sku介绍描述
    private String img; // 默认图片
    private String title; // 标题
    private String subtitle; // 副标题
    private BigDecimal price; // 价格
    private Long saleCount; // 销量
    private List<Attr> saleAttr;
    private String saleValueStr;
    private Long stock;//库存
    private BigDecimal memberPrice;//会员价
    private BigDecimal activityPrice;//活动价
    private Long clickCount; // 浏览量
}
