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
    // spuId
    private Long spuId;
    //spu标题
    private String title;
    //轮播图 spu
//    private List<SpuImages> images;
    private List<String> images;

    //sku
    private List<ProductDetaliSkuVo> skuInfos;
    private ProductDetaliSkuVo checkedProduct;//默认选中的sku
    //获取spu的销售属性组合
    private List<SkuItemSaleAttrVo> saleAttr;


    private Long totalSaleCount; // 销量
    private Long totalClickCount; // 浏览量


    //5、获取spu的规格参数信息
    private List<SpuItemAttrGroupVo> groupAttrs;

    //6、秒杀商品的优惠信息
    private SeckillSkuVo seckillSkuVo;

    private Integer activityType;
}
