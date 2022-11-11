package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IAttrService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.AttrParam;
import com.mdd.product.vo.AttrListVo;
import com.mdd.product.vo.AttrDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.Attr;
import com.mdd.product.mapper.AttrMapper;
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
public class AttrServiceImpl extends ServiceImpl<AttrMapper,Attr> implements IAttrService {

    @Resource
    AttrMapper attrMapper;

    /**
     * 商品属性列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<AttrListVo>
     */
    @Override
    public PageResult<AttrListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("attr_id");

        attrMapper.setSearch(queryWrapper, params, new String[]{
                "like:attrName@attr_name:str",
                "=:searchType@search_type:int",
                "=:icon:str",
                "=:valueSelect@value_select:str",
                "=:attrType@attr_type:int",
                "=:enable:long",
                "=:catelogId@catelog_id:long",
                "=:showDesc@show_desc:int",
        });

        IPage<Attr> iPage = attrMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<AttrListVo> list = new LinkedList<>();
        for(Attr item : iPage.getRecords()) {
            AttrListVo vo = new AttrListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品属性详情
     *
     * @param id 主键参数
     * @return Attr
     */
    @Override
    public AttrDetailVo detail(Long id) {
        Attr model = attrMapper.selectOne(
                new QueryWrapper<Attr>()
                        .eq("attr_id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        AttrDetailVo vo = new AttrDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品属性新增
     *
     * @param attrParam 参数
     */
    @Override
    public void add(AttrParam attrParam) {
        Attr model = new Attr();
        model.setAttrName(attrParam.getAttrName());
        model.setSearchType(attrParam.getSearchType());
        model.setIcon(attrParam.getIcon());
        model.setValueSelect(attrParam.getValueSelect());
        model.setAttrType(attrParam.getAttrType());
        model.setEnable(attrParam.getEnable());
        model.setCatelogId(attrParam.getCatelogId());
        model.setShowDesc(attrParam.getShowDesc());
        attrMapper.insert(model);
    }

    /**
     * 商品属性编辑
     *
     * @param attrParam 参数
     */
    @Override
    public void edit(AttrParam attrParam) {
        Attr model = attrMapper.selectOne(
                new QueryWrapper<Attr>()
                        .eq("attr_id",  attrParam.getAttrId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setAttrId(attrParam.getAttrId());
        model.setAttrName(attrParam.getAttrName());
        model.setSearchType(attrParam.getSearchType());
        model.setIcon(attrParam.getIcon());
        model.setValueSelect(attrParam.getValueSelect());
        model.setAttrType(attrParam.getAttrType());
        model.setEnable(attrParam.getEnable());
        model.setCatelogId(attrParam.getCatelogId());
        model.setShowDesc(attrParam.getShowDesc());
        attrMapper.updateById(model);
    }

    /**
     * 商品属性删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Attr model = attrMapper.selectOne(
                new QueryWrapper<Attr>()
                        .eq("attr_id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        attrMapper.delete(new QueryWrapper<Attr>().eq("attr_id", id));
    }

}
