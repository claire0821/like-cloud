package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsProductAttrValueService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsProductAttrValueParam;
import com.mdd.product.vo.PmsProductAttrValueListVo;
import com.mdd.product.vo.PmsProductAttrValueDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsProductAttrValue;
import com.mdd.product.mapper.PmsProductAttrValueMapper;
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
 * spu属性值实现类
 */
@Service
public class PmsProductAttrValueServiceImpl implements IPmsProductAttrValueService {
        
    @Resource
    PmsProductAttrValueMapper pmsProductAttrValueMapper;

    /**
     * spu属性值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsProductAttrValueListVo>
     */
    @Override
    public PageResult<PmsProductAttrValueListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsProductAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsProductAttrValueMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "=:attrId@attr_id:long",
            "like:attrName@attr_name:str",
            "=:attrValue@attr_value:str",
            "=:attrSort@attr_sort:long",
            "=:quickShow@quick_show:long",
        });

        IPage<PmsProductAttrValue> iPage = pmsProductAttrValueMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsProductAttrValueListVo> list = new LinkedList<>();
        for(PmsProductAttrValue item : iPage.getRecords()) {
            PmsProductAttrValueListVo vo = new PmsProductAttrValueListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu属性值详情
     *
     * @param id 主键参数
     * @return PmsProductAttrValue
     */
    @Override
    public PmsProductAttrValueDetailVo detail(Long id) {
        PmsProductAttrValue model = pmsProductAttrValueMapper.selectOne(
                new QueryWrapper<PmsProductAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsProductAttrValueDetailVo vo = new PmsProductAttrValueDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu属性值新增
     *
     * @param pmsProductAttrValueParam 参数
     */
    @Override
    public void add(PmsProductAttrValueParam pmsProductAttrValueParam) {
        PmsProductAttrValue model = new PmsProductAttrValue();
        model.setSpuId(pmsProductAttrValueParam.getSpuId());
        model.setAttrId(pmsProductAttrValueParam.getAttrId());
        model.setAttrName(pmsProductAttrValueParam.getAttrName());
        model.setAttrValue(pmsProductAttrValueParam.getAttrValue());
        model.setAttrSort(pmsProductAttrValueParam.getAttrSort());
        model.setQuickShow(pmsProductAttrValueParam.getQuickShow());
        pmsProductAttrValueMapper.insert(model);
    }

    /**
     * spu属性值编辑
     *
     * @param pmsProductAttrValueParam 参数
     */
    @Override
    public void edit(PmsProductAttrValueParam pmsProductAttrValueParam) {
        PmsProductAttrValue model = pmsProductAttrValueMapper.selectOne(
                new QueryWrapper<PmsProductAttrValue>()
                    .eq("id",  pmsProductAttrValueParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsProductAttrValueParam.getId());
        model.setSpuId(pmsProductAttrValueParam.getSpuId());
        model.setAttrId(pmsProductAttrValueParam.getAttrId());
        model.setAttrName(pmsProductAttrValueParam.getAttrName());
        model.setAttrValue(pmsProductAttrValueParam.getAttrValue());
        model.setAttrSort(pmsProductAttrValueParam.getAttrSort());
        model.setQuickShow(pmsProductAttrValueParam.getQuickShow());
        pmsProductAttrValueMapper.updateById(model);
    }

    /**
     * spu属性值删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsProductAttrValue model = pmsProductAttrValueMapper.selectOne(
                new QueryWrapper<PmsProductAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsProductAttrValueMapper.delete(new QueryWrapper<PmsProductAttrValue>().eq("id", id));
    }

}
