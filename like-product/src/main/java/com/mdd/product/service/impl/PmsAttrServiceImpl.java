package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsAttrService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsAttrParam;
import com.mdd.product.vo.PmsAttrListVo;
import com.mdd.product.vo.PmsAttrDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsAttr;
import com.mdd.product.mapper.PmsAttrMapper;
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
 * 商品属性实现类
 */
@Service
public class PmsAttrServiceImpl implements IPmsAttrService {
        
    @Resource
    PmsAttrMapper pmsAttrMapper;

    /**
     * 商品属性列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrListVo>
     */
    @Override
    public PageResult<PmsAttrListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("attr_id");

        pmsAttrMapper.setSearch(queryWrapper, params, new String[]{
            "like:attrName@attr_name:str",
            "=:searchType@search_type:long",
            "=:icon:str",
            "=:valueSelect@value_select:str",
            "=:attrType@attr_type:long",
            "=:enable:long",
            "=:catelogId@catelog_id:long",
            "=:showDesc@show_desc:long",
        });

        IPage<PmsAttr> iPage = pmsAttrMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsAttrListVo> list = new LinkedList<>();
        for(PmsAttr item : iPage.getRecords()) {
            PmsAttrListVo vo = new PmsAttrListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品属性详情
     *
     * @param id 主键参数
     * @return PmsAttr
     */
    @Override
    public PmsAttrDetailVo detail(Long id) {
        PmsAttr model = pmsAttrMapper.selectOne(
                new QueryWrapper<PmsAttr>()
                    .eq("attr_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsAttrDetailVo vo = new PmsAttrDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品属性新增
     *
     * @param pmsAttrParam 参数
     */
    @Override
    public void add(PmsAttrParam pmsAttrParam) {
        PmsAttr model = new PmsAttr();
        model.setAttrName(pmsAttrParam.getAttrName());
        model.setSearchType(pmsAttrParam.getSearchType());
        model.setIcon(pmsAttrParam.getIcon());
        model.setValueSelect(pmsAttrParam.getValueSelect());
        model.setAttrType(pmsAttrParam.getAttrType());
        model.setEnable(pmsAttrParam.getEnable());
        model.setCatelogId(pmsAttrParam.getCatelogId());
        model.setShowDesc(pmsAttrParam.getShowDesc());
        pmsAttrMapper.insert(model);
    }

    /**
     * 商品属性编辑
     *
     * @param pmsAttrParam 参数
     */
    @Override
    public void edit(PmsAttrParam pmsAttrParam) {
        PmsAttr model = pmsAttrMapper.selectOne(
                new QueryWrapper<PmsAttr>()
                    .eq("attr_id",  pmsAttrParam.getAttrId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setAttrId(pmsAttrParam.getAttrId());
        model.setAttrName(pmsAttrParam.getAttrName());
        model.setSearchType(pmsAttrParam.getSearchType());
        model.setIcon(pmsAttrParam.getIcon());
        model.setValueSelect(pmsAttrParam.getValueSelect());
        model.setAttrType(pmsAttrParam.getAttrType());
        model.setEnable(pmsAttrParam.getEnable());
        model.setCatelogId(pmsAttrParam.getCatelogId());
        model.setShowDesc(pmsAttrParam.getShowDesc());
        pmsAttrMapper.updateById(model);
    }

    /**
     * 商品属性删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsAttr model = pmsAttrMapper.selectOne(
                new QueryWrapper<PmsAttr>()
                    .eq("attr_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsAttrMapper.delete(new QueryWrapper<PmsAttr>().eq("attr_id", id));
    }

}
