package com.mdd.product.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.product.entity.PmsCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类Mapper
 */
@Mapper
public interface PmsCategoryMapper extends IBaseMapper<PmsCategory> {
}
