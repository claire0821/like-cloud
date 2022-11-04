package com.mdd.product.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.product.entity.PmsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品三级分类Mapper
 */
@Mapper
public interface PmsCategoryMapper extends IBaseMapper<PmsCategory> {
    @Override
    List<PmsCategory> selectList(Wrapper<PmsCategory> queryWrapper);
}
