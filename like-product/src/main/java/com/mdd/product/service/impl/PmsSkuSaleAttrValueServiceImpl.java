package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSkuSaleAttrValueService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSkuSaleAttrValueParam;
import com.mdd.product.vo.PmsSkuSaleAttrValueListVo;
import com.mdd.product.vo.PmsSkuSaleAttrValueDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSkuSaleAttrValue;
import com.mdd.product.mapper.PmsSkuSaleAttrValueMapper;
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
 * sku销售属性&值实现类
 */
@Service
public class PmsSkuSaleAttrValueServiceImpl implements IPmsSkuSaleAttrValueService {
        
    @Resource
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    /**
     * sku销售属性&值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSkuSaleAttrValueListVo>
     */
    @Override
    public PageResult<PmsSkuSaleAttrValueListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSkuSaleAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsSkuSaleAttrValueMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:attrId@attr_id:long",
            "like:attrName@attr_name:str",
            "=:attrValue@attr_value:str",
            "=:attrSort@attr_sort:long",
        });

        IPage<PmsSkuSaleAttrValue> iPage = pmsSkuSaleAttrValueMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSkuSaleAttrValueListVo> list = new LinkedList<>();
        for(PmsSkuSaleAttrValue item : iPage.getRecords()) {
            PmsSkuSaleAttrValueListVo vo = new PmsSkuSaleAttrValueListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * sku销售属性&值详情
     *
     * @param id 主键参数
     * @return PmsSkuSaleAttrValue
     */
    @Override
    public PmsSkuSaleAttrValueDetailVo detail(Long id) {
        PmsSkuSaleAttrValue model = pmsSkuSaleAttrValueMapper.selectOne(
                new QueryWrapper<PmsSkuSaleAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSkuSaleAttrValueDetailVo vo = new PmsSkuSaleAttrValueDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * sku销售属性&值新增
     *
     * @param pmsSkuSaleAttrValueParam 参数
     */
    @Override
    public void add(PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam) {
        PmsSkuSaleAttrValue model = new PmsSkuSaleAttrValue();
        model.setSkuId(pmsSkuSaleAttrValueParam.getSkuId());
        model.setAttrId(pmsSkuSaleAttrValueParam.getAttrId());
        model.setAttrName(pmsSkuSaleAttrValueParam.getAttrName());
        model.setAttrValue(pmsSkuSaleAttrValueParam.getAttrValue());
        model.setAttrSort(pmsSkuSaleAttrValueParam.getAttrSort());
        pmsSkuSaleAttrValueMapper.insert(model);
    }

    /**
     * sku销售属性&值编辑
     *
     * @param pmsSkuSaleAttrValueParam 参数
     */
    @Override
    public void edit(PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam) {
        PmsSkuSaleAttrValue model = pmsSkuSaleAttrValueMapper.selectOne(
                new QueryWrapper<PmsSkuSaleAttrValue>()
                    .eq("id",  pmsSkuSaleAttrValueParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsSkuSaleAttrValueParam.getId());
        model.setSkuId(pmsSkuSaleAttrValueParam.getSkuId());
        model.setAttrId(pmsSkuSaleAttrValueParam.getAttrId());
        model.setAttrName(pmsSkuSaleAttrValueParam.getAttrName());
        model.setAttrValue(pmsSkuSaleAttrValueParam.getAttrValue());
        model.setAttrSort(pmsSkuSaleAttrValueParam.getAttrSort());
        pmsSkuSaleAttrValueMapper.updateById(model);
    }

    /**
     * sku销售属性&值删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSkuSaleAttrValue model = pmsSkuSaleAttrValueMapper.selectOne(
                new QueryWrapper<PmsSkuSaleAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSkuSaleAttrValueMapper.delete(new QueryWrapper<PmsSkuSaleAttrValue>().eq("id", id));
    }

}
