package com.mdd.product.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.product.entity.SkuSaleAttrValue;
import com.mdd.product.vo.SkuItemSaleAttrVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku销售属性&值Mapper
 */
@Mapper
public interface SkuSaleAttrValueMapper extends IBaseMapper<SkuSaleAttrValue> {
    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(@Param("spuId") Long spuId);
    List<String> getSkuSaleAttrValuesAsStringList(@Param("skuId") Long skuId);
}
