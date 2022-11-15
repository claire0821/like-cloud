package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ISkuSaleAttrValueService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SkuSaleAttrValueParam;
import com.mdd.product.vo.SkuSaleAttrValueListVo;
import com.mdd.product.vo.SkuSaleAttrValueDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SkuSaleAttrValue;
import com.mdd.product.mapper.SkuSaleAttrValueMapper;
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
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper,SkuSaleAttrValue> implements ISkuSaleAttrValueService {
        
    @Resource
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    /**
     * sku销售属性&值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuSaleAttrValueListVo>
     */
    @Override
    public PageResult<SkuSaleAttrValueListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SkuSaleAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        skuSaleAttrValueMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:attrId@attr_id:long",
            "like:attrName@attr_name:str",
            "=:attrValue@attr_value:str",
            "=:attrSort@attr_sort:int",
        });

        IPage<SkuSaleAttrValue> iPage = skuSaleAttrValueMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SkuSaleAttrValueListVo> list = new LinkedList<>();
        for(SkuSaleAttrValue item : iPage.getRecords()) {
            SkuSaleAttrValueListVo vo = new SkuSaleAttrValueListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * sku销售属性&值详情
     *
     * @param id 主键参数
     * @return SkuSaleAttrValue
     */
    @Override
    public SkuSaleAttrValueDetailVo detail(Long id) {
        SkuSaleAttrValue model = skuSaleAttrValueMapper.selectOne(
                new QueryWrapper<SkuSaleAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SkuSaleAttrValueDetailVo vo = new SkuSaleAttrValueDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * sku销售属性&值新增
     *
     * @param skuSaleAttrValueParam 参数
     */
    @Override
    public void add(SkuSaleAttrValueParam skuSaleAttrValueParam) {
        SkuSaleAttrValue model = new SkuSaleAttrValue();
        model.setSkuId(skuSaleAttrValueParam.getSkuId());
        model.setAttrId(skuSaleAttrValueParam.getAttrId());
        model.setAttrName(skuSaleAttrValueParam.getAttrName());
        model.setAttrValue(skuSaleAttrValueParam.getAttrValue());
        model.setAttrSort(skuSaleAttrValueParam.getAttrSort());
        skuSaleAttrValueMapper.insert(model);
    }

    /**
     * sku销售属性&值编辑
     *
     * @param skuSaleAttrValueParam 参数
     */
    @Override
    public void edit(SkuSaleAttrValueParam skuSaleAttrValueParam) {
        SkuSaleAttrValue model = skuSaleAttrValueMapper.selectOne(
                new QueryWrapper<SkuSaleAttrValue>()
                    .eq("id",  skuSaleAttrValueParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(skuSaleAttrValueParam.getId());
        model.setSkuId(skuSaleAttrValueParam.getSkuId());
        model.setAttrId(skuSaleAttrValueParam.getAttrId());
        model.setAttrName(skuSaleAttrValueParam.getAttrName());
        model.setAttrValue(skuSaleAttrValueParam.getAttrValue());
        model.setAttrSort(skuSaleAttrValueParam.getAttrSort());
        skuSaleAttrValueMapper.updateById(model);
    }

    /**
     * sku销售属性&值删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SkuSaleAttrValue model = skuSaleAttrValueMapper.selectOne(
                new QueryWrapper<SkuSaleAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        skuSaleAttrValueMapper.delete(new QueryWrapper<SkuSaleAttrValue>().eq("id", id));
    }

}
