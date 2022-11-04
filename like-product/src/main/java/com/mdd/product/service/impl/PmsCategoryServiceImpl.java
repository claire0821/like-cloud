package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsCategoryService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsCategoryParam;
import com.mdd.product.vo.PmsCategoryListVo;
import com.mdd.product.vo.PmsCategoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsCategory;
import com.mdd.product.mapper.PmsCategoryMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品三级分类实现类
 */
@Service
public class PmsCategoryServiceImpl implements IPmsCategoryService {
        
    @Resource
    PmsCategoryMapper pmsCategoryMapper;

    /**
     * 商品三级分类列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryListVo>
     */
    @Override
    public PageResult<PmsCategoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "cat_id"));

        pmsCategoryMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:parentCid@parent_cid:long",
            "=:catLevel@cat_level:long",
            "=:showStatus@show_status:long",
            "=:sort:long",
            "=:icon:str",
            "=:productUnit@product_unit:str",
            "=:productCount@product_count:long",
        });

        IPage<PmsCategory> iPage = pmsCategoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsCategoryListVo> list = new LinkedList<>();
        for(PmsCategory item : iPage.getRecords()) {
            PmsCategoryListVo vo = new PmsCategoryListVo();
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
    public List<PmsCategory> listWithTree() {
        final List<PmsCategory> pmsCategories = pmsCategoryMapper.selectList(null);
        return pmsCategories;
    }

    /**
     * 商品三级分类详情
     *
     * @param id 主键参数
     * @return PmsCategory
     */
    @Override
    public PmsCategoryDetailVo detail(Long id) {
        PmsCategory model = pmsCategoryMapper.selectOne(
                new QueryWrapper<PmsCategory>()
                    .eq("cat_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsCategoryDetailVo vo = new PmsCategoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品三级分类新增
     *
     * @param pmsCategoryParam 参数
     */
    @Override
    public void add(PmsCategoryParam pmsCategoryParam) {
        PmsCategory model = new PmsCategory();
        model.setName(pmsCategoryParam.getName());
        model.setParentCid(pmsCategoryParam.getParentCid());
        model.setCatLevel(pmsCategoryParam.getCatLevel());
        model.setShowStatus(pmsCategoryParam.getShowStatus());
        model.setSort(pmsCategoryParam.getSort());
        model.setIcon(pmsCategoryParam.getIcon());
        model.setProductUnit(pmsCategoryParam.getProductUnit());
        model.setProductCount(pmsCategoryParam.getProductCount());
        pmsCategoryMapper.insert(model);
    }

    /**
     * 商品三级分类编辑
     *
     * @param pmsCategoryParam 参数
     */
    @Override
    public void edit(PmsCategoryParam pmsCategoryParam) {
        PmsCategory model = pmsCategoryMapper.selectOne(
                new QueryWrapper<PmsCategory>()
                    .eq("cat_id",  pmsCategoryParam.getCatId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setCatId(pmsCategoryParam.getCatId());
        model.setName(pmsCategoryParam.getName());
        model.setParentCid(pmsCategoryParam.getParentCid());
        model.setCatLevel(pmsCategoryParam.getCatLevel());
        model.setShowStatus(pmsCategoryParam.getShowStatus());
        model.setSort(pmsCategoryParam.getSort());
        model.setIcon(pmsCategoryParam.getIcon());
        model.setProductUnit(pmsCategoryParam.getProductUnit());
        model.setProductCount(pmsCategoryParam.getProductCount());
        pmsCategoryMapper.updateById(model);
    }

    /**
     * 商品三级分类删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsCategory model = pmsCategoryMapper.selectOne(
                new QueryWrapper<PmsCategory>()
                    .eq("cat_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsCategoryMapper.delete(new QueryWrapper<PmsCategory>().eq("cat_id", id));
    }

}
