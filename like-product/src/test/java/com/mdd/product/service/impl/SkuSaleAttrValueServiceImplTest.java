package com.mdd.product.service.impl;

import com.mdd.product.service.ISkuSaleAttrValueService;
import com.mdd.product.vo.ProductDetaliVo;
import com.mdd.product.vo.SkuItemSaleAttrVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class SkuSaleAttrValueServiceImplTest {

    @Autowired
    ISkuSaleAttrValueService iSkuSaleAttrValueService;

    @Test
   public   void getSaleAttrBySpuId() {
        final List<SkuItemSaleAttrVo> saleAttrBySpuId = iSkuSaleAttrValueService.getSaleAttrBySpuId(8L);
        System.out.println(saleAttrBySpuId);
    }
}