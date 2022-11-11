package com.mdd.product.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.product.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品三级分类Mapper
 */
@Mapper
public interface CategoryMapper extends IBaseMapper<Category> {
    @Override
    List<Category> selectList(Wrapper<Category> queryWrapper);
}
