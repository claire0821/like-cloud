package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.entity.SkuInfo;
import com.mdd.product.service.ISkuImagesService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SkuImagesParam;
import com.mdd.product.vo.SkuImagesListVo;
import com.mdd.product.vo.SkuImagesDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SkuImages;
import com.mdd.product.mapper.SkuImagesMapper;
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
 * sku图片实现类
 */
@Service
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper,SkuImages> implements ISkuImagesService {
        
    @Resource
    SkuImagesMapper skuImagesMapper;

    /**
     * sku图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuImagesListVo>
     */
    @Override
    public PageResult<SkuImagesListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SkuImages> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        skuImagesMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:imgUrl@img_url:str",
            "=:imgSort@img_sort:int",
            "=:defaultImg@default_img:int",
        });

        IPage<SkuImages> iPage = skuImagesMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SkuImagesListVo> list = new LinkedList<>();
        for(SkuImages item : iPage.getRecords()) {
            SkuImagesListVo vo = new SkuImagesListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * sku图片详情
     *
     * @param id 主键参数
     * @return SkuImages
     */
    @Override
    public SkuImagesDetailVo detail(Long id) {
        SkuImages model = skuImagesMapper.selectOne(
                new QueryWrapper<SkuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SkuImagesDetailVo vo = new SkuImagesDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * sku图片新增
     *
     * @param skuImagesParam 参数
     */
    @Override
    public void add(SkuImagesParam skuImagesParam) {
        SkuImages model = new SkuImages();
        model.setSkuId(skuImagesParam.getSkuId());
        model.setImgUrl(skuImagesParam.getImgUrl());
        model.setImgSort(skuImagesParam.getImgSort());
        model.setDefaultImg(skuImagesParam.getDefaultImg());
        skuImagesMapper.insert(model);
    }

    /**
     * sku图片编辑
     *
     * @param skuImagesParam 参数
     */
    @Override
    public void edit(SkuImagesParam skuImagesParam) {
        SkuImages model = skuImagesMapper.selectOne(
                new QueryWrapper<SkuImages>()
                    .eq("id",  skuImagesParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(skuImagesParam.getId());
        model.setSkuId(skuImagesParam.getSkuId());
        model.setImgUrl(skuImagesParam.getImgUrl());
        model.setImgSort(skuImagesParam.getImgSort());
        model.setDefaultImg(skuImagesParam.getDefaultImg());
        skuImagesMapper.updateById(model);
    }

    /**
     * sku图片删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SkuImages model = skuImagesMapper.selectOne(
                new QueryWrapper<SkuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        skuImagesMapper.delete(new QueryWrapper<SkuImages>().eq("id", id));
    }

}
