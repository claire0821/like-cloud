package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IProductAttrValueService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.ProductAttrValueParam;
import com.mdd.product.vo.ProductAttrValueListVo;
import com.mdd.product.vo.ProductAttrValueDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.ProductAttrValue;
import com.mdd.product.mapper.ProductAttrValueMapper;
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
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper,ProductAttrValue> implements IProductAttrValueService {
        
    @Resource
    ProductAttrValueMapper productAttrValueMapper;

    /**
     * spu属性值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ProductAttrValueListVo>
     */
    @Override
    public PageResult<ProductAttrValueListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<ProductAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        productAttrValueMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "=:attrId@attr_id:long",
            "like:attrName@attr_name:str",
            "=:attrValue@attr_value:str",
            "=:attrSort@attr_sort:int",
            "=:quickShow@quick_show:int",
        });

        IPage<ProductAttrValue> iPage = productAttrValueMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<ProductAttrValueListVo> list = new LinkedList<>();
        for(ProductAttrValue item : iPage.getRecords()) {
            ProductAttrValueListVo vo = new ProductAttrValueListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu属性值详情
     *
     * @param id 主键参数
     * @return ProductAttrValue
     */
    @Override
    public ProductAttrValueDetailVo detail(Long id) {
        ProductAttrValue model = productAttrValueMapper.selectOne(
                new QueryWrapper<ProductAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        ProductAttrValueDetailVo vo = new ProductAttrValueDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu属性值新增
     *
     * @param productAttrValueParam 参数
     */
    @Override
    public void add(ProductAttrValueParam productAttrValueParam) {
        ProductAttrValue model = new ProductAttrValue();
        model.setSpuId(productAttrValueParam.getSpuId());
        model.setAttrId(productAttrValueParam.getAttrId());
        model.setAttrName(productAttrValueParam.getAttrName());
        model.setAttrValue(productAttrValueParam.getAttrValue());
        model.setAttrSort(productAttrValueParam.getAttrSort());
        model.setQuickShow(productAttrValueParam.getQuickShow());
        productAttrValueMapper.insert(model);
    }

    /**
     * spu属性值编辑
     *
     * @param productAttrValueParam 参数
     */
    @Override
    public void edit(ProductAttrValueParam productAttrValueParam) {
        ProductAttrValue model = productAttrValueMapper.selectOne(
                new QueryWrapper<ProductAttrValue>()
                    .eq("id",  productAttrValueParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(productAttrValueParam.getId());
        model.setSpuId(productAttrValueParam.getSpuId());
        model.setAttrId(productAttrValueParam.getAttrId());
        model.setAttrName(productAttrValueParam.getAttrName());
        model.setAttrValue(productAttrValueParam.getAttrValue());
        model.setAttrSort(productAttrValueParam.getAttrSort());
        model.setQuickShow(productAttrValueParam.getQuickShow());
        productAttrValueMapper.updateById(model);
    }

    /**
     * spu属性值删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        ProductAttrValue model = productAttrValueMapper.selectOne(
                new QueryWrapper<ProductAttrValue>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        productAttrValueMapper.delete(new QueryWrapper<ProductAttrValue>().eq("id", id));
    }

    @Override
    public void saveProductAttr(List<ProductAttrValue> collect) {
        this.saveBatch(collect);
    }

}
