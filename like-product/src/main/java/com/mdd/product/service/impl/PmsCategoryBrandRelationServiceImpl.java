package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsCategoryBrandRelationService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsCategoryBrandRelationParam;
import com.mdd.product.vo.PmsCategoryBrandRelationListVo;
import com.mdd.product.vo.PmsCategoryBrandRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsCategoryBrandRelation;
import com.mdd.product.mapper.PmsCategoryBrandRelationMapper;
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
 * 品牌分类关联实现类
 */
@Service
public class PmsCategoryBrandRelationServiceImpl implements IPmsCategoryBrandRelationService {
        
    @Resource
    PmsCategoryBrandRelationMapper pmsCategoryBrandRelationMapper;

    /**
     * 品牌分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCategoryBrandRelationListVo>
     */
    @Override
    public PageResult<PmsCategoryBrandRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsCategoryBrandRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsCategoryBrandRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:brandId@brand_id:long",
            "=:catelogId@catelog_id:long",
            "like:brandName@brand_name:str",
            "like:catelogName@catelog_name:str",
        });

        IPage<PmsCategoryBrandRelation> iPage = pmsCategoryBrandRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsCategoryBrandRelationListVo> list = new LinkedList<>();
        for(PmsCategoryBrandRelation item : iPage.getRecords()) {
            PmsCategoryBrandRelationListVo vo = new PmsCategoryBrandRelationListVo();
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
        PmsCategoryBrandRelation model = pmsCategoryBrandRelationMapper.selectOne(
                new QueryWrapper<PmsCategoryBrandRelation>()
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
     * @param pmsCategoryBrandRelationParam 参数
     */
    @Override
    public void add(PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam) {
        PmsCategoryBrandRelation model = new PmsCategoryBrandRelation();
        model.setBrandId(pmsCategoryBrandRelationParam.getBrandId());
        model.setCatelogId(pmsCategoryBrandRelationParam.getCatelogId());
        model.setBrandName(pmsCategoryBrandRelationParam.getBrandName());
        model.setCatelogName(pmsCategoryBrandRelationParam.getCatelogName());
        pmsCategoryBrandRelationMapper.insert(model);
    }

    /**
     * 品牌分类关联编辑
     *
     * @param pmsCategoryBrandRelationParam 参数
     */
    @Override
    public void edit(PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam) {
        PmsCategoryBrandRelation model = pmsCategoryBrandRelationMapper.selectOne(
                new QueryWrapper<PmsCategoryBrandRelation>()
                    .eq("id",  pmsCategoryBrandRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsCategoryBrandRelationParam.getId());
        model.setBrandId(pmsCategoryBrandRelationParam.getBrandId());
        model.setCatelogId(pmsCategoryBrandRelationParam.getCatelogId());
        model.setBrandName(pmsCategoryBrandRelationParam.getBrandName());
        model.setCatelogName(pmsCategoryBrandRelationParam.getCatelogName());
        pmsCategoryBrandRelationMapper.updateById(model);
    }

    /**
     * 品牌分类关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsCategoryBrandRelation model = pmsCategoryBrandRelationMapper.selectOne(
                new QueryWrapper<PmsCategoryBrandRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsCategoryBrandRelationMapper.delete(new QueryWrapper<PmsCategoryBrandRelation>().eq("id", id));
    }

}
