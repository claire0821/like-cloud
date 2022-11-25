package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.product.service.ICategoryBrandRelationService;
import com.mdd.product.service.ICategoryService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.CategoryParam;
import com.mdd.product.vo.CategoryListVo;
import com.mdd.product.vo.CategoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.Category;
import com.mdd.product.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品三级分类实现类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
        
    @Resource
    CategoryMapper categoryMapper;

    @Autowired
    ICategoryBrandRelationService iCategoryBrandRelationService;
    /**
     * 商品三级分类列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryListVo>
     */
    @Override
    public PageResult<CategoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "cat_id"));

        categoryMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:parentCid@parent_cid:long",
            "=:catLevel@cat_level:long",
            "=:showStatus@show_status:long",
            "=:sort:long",
            "=:icon:str",
            "=:productUnit@product_unit:str",
            "=:productCount@product_count:long",
        });

        IPage<Category> iPage = categoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CategoryListVo> list = new LinkedList<>();
        for(Category item : iPage.getRecords()) {
            CategoryListVo vo = new CategoryListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 查询所有
     * @return List<PmsCategory>
     */
    @Override
    public List<Category> listWithTree() {
        final List<Category> pmsCategories = categoryMapper.selectList(null);
        return pmsCategories;
    }

    /**
     * 商品三级分类详情
     *
     * @param id 主键参数
     * @return PmsCategory
     */
    @Override
    public CategoryDetailVo detail(Long id) {
        Category model = categoryMapper.selectOne(
                new QueryWrapper<Category>()
                    .eq("cat_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CategoryDetailVo vo = new CategoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品三级分类新增
     *
     * @param categoryParam 参数
     */
    @Override
    public void add(CategoryParam categoryParam) {
        Category model = new Category();
        model.setName(categoryParam.getName());
        model.setParentCid(categoryParam.getParentCid());
        model.setCatLevel(categoryParam.getCatLevel());
        model.setShowStatus(categoryParam.getShowStatus());
        model.setSort(categoryParam.getSort());
        model.setIcon(categoryParam.getIcon());
        model.setProductUnit(categoryParam.getProductUnit());
        model.setProductCount(categoryParam.getProductCount());
        categoryMapper.insert(model);
    }

    /**
     * 商品三级分类编辑
     *
     * @param categoryParam 参数
     */
    @Override
    public void edit(CategoryParam categoryParam) {
        Category model = categoryMapper.selectOne(
                new QueryWrapper<Category>()
                    .eq("cat_id",  categoryParam.getCatId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setCatId(categoryParam.getCatId());
        model.setName(categoryParam.getName());
        model.setParentCid(categoryParam.getParentCid());
        model.setCatLevel(categoryParam.getCatLevel());
        model.setShowStatus(categoryParam.getShowStatus());
        model.setSort(categoryParam.getSort());
        model.setIcon(categoryParam.getIcon());
        model.setProductUnit(categoryParam.getProductUnit());
        model.setProductCount(categoryParam.getProductCount());
        categoryMapper.updateById(model);
    }

    /**
     * 商品三级分类删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Category model = categoryMapper.selectOne(
                new QueryWrapper<Category>()
                    .eq("cat_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        categoryMapper.delete(new QueryWrapper<Category>().eq("cat_id", id));
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 逻辑删除
        categoryMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId,paths);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * 级联更新所有关联的数据
     * @param category
     */
    @Transactional
    @Override
    public void updateCascade(Category category) {
        this.updateById(category);
        iCategoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    @Override
    public List<CategoryListVo> getByParentID(Long parentId) {
        return null;
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        Category byId = this.getById(catelogId);
        if(byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

}
