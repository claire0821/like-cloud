package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.product.entity.Brand;
import com.mdd.product.entity.Category;
import com.mdd.product.mapper.BrandMapper;
import com.mdd.product.mapper.CategoryMapper;
import com.mdd.product.service.ICategoryBrandRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.CategoryBrandRelationParam;
import com.mdd.product.vo.CategoryBrandRelationListVo;
import com.mdd.product.vo.PmsCategoryBrandRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.CategoryBrandRelation;
import com.mdd.product.mapper.CategoryBrandRelationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 品牌分类关联实现类
 */
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements ICategoryBrandRelationService {

    @Resource
    CategoryBrandRelationMapper categoryBrandRelationMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    CategoryMapper categoryMapper;
    /**
     * 品牌分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryBrandRelationListVo>
     */
    @Override
    public PageResult<CategoryBrandRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<CategoryBrandRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        categoryBrandRelationMapper.setSearch(queryWrapper, params, new String[]{
                "=:brandId@brand_id:long",
                "=:catelogId@catelog_id:long",
                "like:brandName@brand_name:str",
                "like:catelogName@catelog_name:str",
        });

        IPage<CategoryBrandRelation> iPage = categoryBrandRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CategoryBrandRelationListVo> list = new LinkedList<>();
        for(CategoryBrandRelation item : iPage.getRecords()) {
            CategoryBrandRelationListVo vo = new CategoryBrandRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 品牌分类关联详情
     *
     * @param id 主键参数
     * @return PmsCategoryBrandRelation
     */
    @Override
    public PmsCategoryBrandRelationDetailVo detail(Long id) {
        CategoryBrandRelation model = categoryBrandRelationMapper.selectOne(
                new QueryWrapper<CategoryBrandRelation>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsCategoryBrandRelationDetailVo vo = new PmsCategoryBrandRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 品牌分类关联新增
     *
     * @param categoryBrandRelationParam 参数
     */
    @Override
    public void add(CategoryBrandRelationParam categoryBrandRelationParam) {
        CategoryBrandRelation model = new CategoryBrandRelation();
        model.setBrandId(categoryBrandRelationParam.getBrandId());
        model.setCatelogId(categoryBrandRelationParam.getCatelogId());
        model.setBrandName(categoryBrandRelationParam.getBrandName());
        model.setCatelogName(categoryBrandRelationParam.getCatelogName());
        categoryBrandRelationMapper.insert(model);
    }

    /**
     * 品牌分类关联编辑
     *
     * @param categoryBrandRelationParam 参数
     */
    @Override
    public void edit(CategoryBrandRelationParam categoryBrandRelationParam) {
        CategoryBrandRelation model = categoryBrandRelationMapper.selectOne(
                new QueryWrapper<CategoryBrandRelation>()
                        .eq("id",  categoryBrandRelationParam.getId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(categoryBrandRelationParam.getId());
        model.setBrandId(categoryBrandRelationParam.getBrandId());
        model.setCatelogId(categoryBrandRelationParam.getCatelogId());
        model.setBrandName(categoryBrandRelationParam.getBrandName());
        model.setCatelogName(categoryBrandRelationParam.getCatelogName());
        categoryBrandRelationMapper.updateById(model);
    }

    /**
     * 品牌分类关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        CategoryBrandRelation model = categoryBrandRelationMapper.selectOne(
                new QueryWrapper<CategoryBrandRelation>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        categoryBrandRelationMapper.delete(new QueryWrapper<CategoryBrandRelation>().eq("id", id));
    }

    @Override
    public void saveDetail(CategoryBrandRelation categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        //1、查询详细名字
        Brand brand = brandMapper.selectById(brandId);
        Category category = categoryMapper.selectById(catelogId);

        categoryBrandRelation.setBrandName(brand.getName());
        categoryBrandRelation.setCatelogName(category.getName());

        this.save(categoryBrandRelation);
    }

    @Override
    public void updateCategory(Long catId, String name) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("catelog_id",catId);
        updateWrapper.set("catelog_name",name);
        update(updateWrapper);
    }

}
