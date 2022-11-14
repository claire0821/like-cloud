package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IAttrAttrgroupRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.AttrAttrgroupRelationParam;
import com.mdd.product.vo.AttrAttrgroupRelationListVo;
import com.mdd.product.vo.AttrAttrgroupRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.AttrAttrgroupRelation;
import com.mdd.product.mapper.AttrAttrgroupRelationMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import com.mdd.product.vo.AttrGroupListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 属性&属性分组关联实现类
 */
@Service
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationMapper,AttrAttrgroupRelation> implements IAttrAttrgroupRelationService {

    @Resource
    AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    /**
     * 属性&属性分组关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<AttrAttrgroupRelationListVo>
     */
    @Override
    public PageResult<AttrAttrgroupRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<AttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        attrAttrgroupRelationMapper.setSearch(queryWrapper, params, new String[]{
                "=:attrId@attr_id:long",
                "=:attrGroupId@attr_group_id:long",
                "=:attrSort@attr_sort:int",
        });

        IPage<AttrAttrgroupRelation> iPage = attrAttrgroupRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<AttrAttrgroupRelationListVo> list = new LinkedList<>();
        for(AttrAttrgroupRelation item : iPage.getRecords()) {
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
     * @return AttrAttrgroupRelation
     */
    @Override
    public AttrAttrgroupRelationDetailVo detail(Long id) {
        AttrAttrgroupRelation model = attrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<AttrAttrgroupRelation>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        AttrAttrgroupRelationDetailVo vo = new AttrAttrgroupRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 属性&属性分组关联新增
     *
     * @param attrAttrgroupRelationParam 参数
     */
    @Override
    public void add(AttrAttrgroupRelationParam attrAttrgroupRelationParam) {
        AttrAttrgroupRelation model = new AttrAttrgroupRelation();
        model.setAttrId(attrAttrgroupRelationParam.getAttrId());
        model.setAttrGroupId(attrAttrgroupRelationParam.getAttrGroupId());
        model.setAttrSort(attrAttrgroupRelationParam.getAttrSort());
        attrAttrgroupRelationMapper.insert(model);
    }

    /**
     * 属性&属性分组关联编辑
     *
     * @param attrAttrgroupRelationParam 参数
     */
    @Override
    public void edit(AttrAttrgroupRelationParam attrAttrgroupRelationParam) {
        AttrAttrgroupRelation model = attrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<AttrAttrgroupRelation>()
                        .eq("id",  attrAttrgroupRelationParam.getId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(attrAttrgroupRelationParam.getId());
        model.setAttrId(attrAttrgroupRelationParam.getAttrId());
        model.setAttrGroupId(attrAttrgroupRelationParam.getAttrGroupId());
        model.setAttrSort(attrAttrgroupRelationParam.getAttrSort());
        attrAttrgroupRelationMapper.updateById(model);
    }

    /**
     * 属性&属性分组关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        AttrAttrgroupRelation model = attrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<AttrAttrgroupRelation>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        attrAttrgroupRelationMapper.delete(new QueryWrapper<AttrAttrgroupRelation>().eq("id", id));
    }

    @Override
    public void deleteBatchRelation(List<AttrAttrgroupRelation> attrAttrgroupRelations) {
        for (AttrAttrgroupRelation entity : attrAttrgroupRelations) {
            attrAttrgroupRelationMapper.delete(
                    new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", entity.getAttrId()).eq("attr_group_id",entity.getAttrGroupId()));
        }
    }

    @Override
    public void saveBatch(List<AttrAttrgroupRelationListVo> vos) {
        List<AttrAttrgroupRelation> collect = vos.stream().map(item -> {
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }
}
