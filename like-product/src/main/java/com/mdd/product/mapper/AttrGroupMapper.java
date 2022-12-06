package com.mdd.product.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.product.entity.AttrGroup;
import com.mdd.product.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组Mapper
 */
@Mapper
public interface AttrGroupMapper extends IBaseMapper<AttrGroup> {
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
