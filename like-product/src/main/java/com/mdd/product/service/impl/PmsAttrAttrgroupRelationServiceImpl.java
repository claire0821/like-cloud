package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.product.service.IPmsAttrAttrgroupRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsAttrAttrgroupRelationParam;
import com.mdd.product.vo.AttrAttrgroupRelationListVo;
import com.mdd.product.vo.PmsAttrAttrgroupRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsAttrAttrgroupRelation;
import com.mdd.product.mapper.PmsAttrAttrgroupRelationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 属性&属性分组关联实现类
 */
@Service
public class PmsAttrAttrgroupRelationServiceImpl implements IPmsAttrAttrgroupRelationService {
        
    @Resource
    PmsAttrAttrgroupRelationMapper pmsAttrAttrgroupRelationMapper;

    /**
     * 属性&属性分组关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrAttrgroupRelationListVo>
     */
    @Override
    public PageResult<AttrAttrgroupRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsAttrAttrgroupRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:attrId@attr_id:long",
            "=:attrGroupId@attr_group_id:long",
            "=:attrSort@attr_sort:long",
        });

        IPage<PmsAttrAttrgroupRelation> iPage = pmsAttrAttrgroupRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<AttrAttrgroupRelationListVo> list = new LinkedList<>();
        for(PmsAttrAttrgroupRelation item : iPage.getRecords()) {
            AttrAttrgroupRelationListVo vo = new AttrAttrgroupRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 属性&属性分组关联详情
     *
     * @param id 主键参数
     * @return PmsAttrAttrgroupRelation
     */
    @Override
    public PmsAttrAttrgroupRelationDetailVo detail(Long id) {
        PmsAttrAttrgroupRelation model = pmsAttrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<PmsAttrAttrgroupRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsAttrAttrgroupRelationDetailVo vo = new PmsAttrAttrgroupRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 属性&属性分组关联新增
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     */
    @Override
    public void add(PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam) {
        PmsAttrAttrgroupRelation model = new PmsAttrAttrgroupRelation();
        model.setAttrId(pmsAttrAttrgroupRelationParam.getAttrId());
        model.setAttrGroupId(pmsAttrAttrgroupRelationParam.getAttrGroupId());
        model.setAttrSort(pmsAttrAttrgroupRelationParam.getAttrSort());
        pmsAttrAttrgroupRelationMapper.insert(model);
    }

    /**
     * 属性&属性分组关联编辑
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     */
    @Override
    public void edit(PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam) {
        PmsAttrAttrgroupRelation model = pmsAttrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<PmsAttrAttrgroupRelation>()
                    .eq("id",  pmsAttrAttrgroupRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsAttrAttrgroupRelationParam.getId());
        model.setAttrId(pmsAttrAttrgroupRelationParam.getAttrId());
        model.setAttrGroupId(pmsAttrAttrgroupRelationParam.getAttrGroupId());
        model.setAttrSort(pmsAttrAttrgroupRelationParam.getAttrSort());
        pmsAttrAttrgroupRelationMapper.updateById(model);
    }

    /**
     * 属性&属性分组关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsAttrAttrgroupRelation model = pmsAttrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<PmsAttrAttrgroupRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsAttrAttrgroupRelationMapper.delete(new QueryWrapper<PmsAttrAttrgroupRelation>().eq("id", id));
    }

}
