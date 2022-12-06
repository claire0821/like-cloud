package com.mdd.product.vo;

import com.mdd.product.entity.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description: 商品详情
 * @author: Claire
 * @create: 2022-12-02 13:58
 **/
@Data
public class ProductDetaliVo {
    //1、sku基本信息的获取  pms_sku_info
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

    private SkuInfo info;

    private boolean hasStock = true;

    //2、sku的图片信息    pms_sku_images
    private List<SkuImages> images;

    //3、获取spu的销售属性组合
    private List<SkuItemSaleAttrVo> saleAttr;

    //4、获取spu的介绍
    private SpuInfoDesc desc;

    //5、获取spu的规格参数信息
    private List<SpuItemAttrGroupVo> groupAttrs;

    //6、秒杀商品的优惠信息
    private SeckillSkuVo seckillSkuVo;
}
