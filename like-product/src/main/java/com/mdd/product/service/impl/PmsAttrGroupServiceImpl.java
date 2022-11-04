package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsAttrGroupService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsAttrGroupParam;
import com.mdd.product.vo.PmsAttrGroupListVo;
import com.mdd.product.vo.PmsAttrGroupDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsAttrGroup;
import com.mdd.product.mapper.PmsAttrGroupMapper;
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
 * 属性分组实现类
 */
@Service
public class PmsAttrGroupServiceImpl implements IPmsAttrGroupService {
        
    @Resource
    PmsAttrGroupMapper pmsAttrGroupMapper;

    /**
     * 属性分组列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrGroupListVo>
     */
    @Override
    public PageResult<PmsAttrGroupListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "attr_group_id"));

        pmsAttrGroupMapper.setSearch(queryWrapper, params, new String[]{
            "like:attrGroupName@attr_group_name:str",
            "=:sort:long",
            "=:descript:str",
            "=:icon:str",
            "=:catelogId@catelog_id:long",
        });

        IPage<PmsAttrGroup> iPage = pmsAttrGroupMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsAttrGroupListVo> list = new LinkedList<>();
        for(PmsAttrGroup item : iPage.getRecords()) {
            PmsAttrGroupListVo vo = new PmsAttrGroupListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 属性分组详情
     *
     * @param id 主键参数
     * @return PmsAttrGroup
     */
    @Override
    public PmsAttrGroupDetailVo detail(Long id) {
        PmsAttrGroup model = pmsAttrGroupMapper.selectOne(
                new QueryWrapper<PmsAttrGroup>()
                    .eq("attr_group_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsAttrGroupDetailVo vo = new PmsAttrGroupDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 属性分组新增
     *
     * @param pmsAttrGroupParam 参数
     */
    @Override
    public void add(PmsAttrGroupParam pmsAttrGroupParam) {
        PmsAttrGroup model = new PmsAttrGroup();
        model.setAttrGroupName(pmsAttrGroupParam.getAttrGroupName());
        model.setSort(pmsAttrGroupParam.getSort());
        model.setDescript(pmsAttrGroupParam.getDescript());
        model.setIcon(pmsAttrGroupParam.getIcon());
        model.setCatelogId(pmsAttrGroupParam.getCatelogId());
        pmsAttrGroupMapper.insert(model);
    }

    /**
     * 属性分组编辑
     *
     * @param pmsAttrGroupParam 参数
     */
    @Override
    public void edit(PmsAttrGroupParam pmsAttrGroupParam) {
        PmsAttrGroup model = pmsAttrGroupMapper.selectOne(
                new QueryWrapper<PmsAttrGroup>()
                    .eq("attr_group_id",  pmsAttrGroupParam.getAttrGroupId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setAttrGroupId(pmsAttrGroupParam.getAttrGroupId());
        model.setAttrGroupName(pmsAttrGroupParam.getAttrGroupName());
        model.setSort(pmsAttrGroupParam.getSort());
        model.setDescript(pmsAttrGroupParam.getDescript());
        model.setIcon(pmsAttrGroupParam.getIcon());
        model.setCatelogId(pmsAttrGroupParam.getCatelogId());
        pmsAttrGroupMapper.updateById(model);
    }

    /**
     * 属性分组删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsAttrGroup model = pmsAttrGroupMapper.selectOne(
                new QueryWrapper<PmsAttrGroup>()
                    .eq("attr_group_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsAttrGroupMapper.delete(new QueryWrapper<PmsAttrGroup>().eq("attr_group_id", id));
    }

}
