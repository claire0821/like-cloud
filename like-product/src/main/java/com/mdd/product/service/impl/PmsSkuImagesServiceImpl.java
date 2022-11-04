package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSkuImagesService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSkuImagesParam;
import com.mdd.product.vo.PmsSkuImagesListVo;
import com.mdd.product.vo.PmsSkuImagesDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSkuImages;
import com.mdd.product.mapper.PmsSkuImagesMapper;
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
public class PmsSkuImagesServiceImpl implements IPmsSkuImagesService {
        
    @Resource
    PmsSkuImagesMapper pmsSkuImagesMapper;

    /**
     * sku图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSkuImagesListVo>
     */
    @Override
    public PageResult<PmsSkuImagesListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSkuImages> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsSkuImagesMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:imgUrl@img_url:str",
            "=:imgSort@img_sort:long",
            "=:defaultImg@default_img:long",
        });

        IPage<PmsSkuImages> iPage = pmsSkuImagesMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSkuImagesListVo> list = new LinkedList<>();
        for(PmsSkuImages item : iPage.getRecords()) {
            PmsSkuImagesListVo vo = new PmsSkuImagesListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * sku图片详情
     *
     * @param id 主键参数
     * @return PmsSkuImages
     */
    @Override
    public PmsSkuImagesDetailVo detail(Long id) {
        PmsSkuImages model = pmsSkuImagesMapper.selectOne(
                new QueryWrapper<PmsSkuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSkuImagesDetailVo vo = new PmsSkuImagesDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * sku图片新增
     *
     * @param pmsSkuImagesParam 参数
     */
    @Override
    public void add(PmsSkuImagesParam pmsSkuImagesParam) {
        PmsSkuImages model = new PmsSkuImages();
        model.setSkuId(pmsSkuImagesParam.getSkuId());
        model.setImgUrl(pmsSkuImagesParam.getImgUrl());
        model.setImgSort(pmsSkuImagesParam.getImgSort());
        model.setDefaultImg(pmsSkuImagesParam.getDefaultImg());
        pmsSkuImagesMapper.insert(model);
    }

    /**
     * sku图片编辑
     *
     * @param pmsSkuImagesParam 参数
     */
    @Override
    public void edit(PmsSkuImagesParam pmsSkuImagesParam) {
        PmsSkuImages model = pmsSkuImagesMapper.selectOne(
                new QueryWrapper<PmsSkuImages>()
                    .eq("id",  pmsSkuImagesParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsSkuImagesParam.getId());
        model.setSkuId(pmsSkuImagesParam.getSkuId());
        model.setImgUrl(pmsSkuImagesParam.getImgUrl());
        model.setImgSort(pmsSkuImagesParam.getImgSort());
        model.setDefaultImg(pmsSkuImagesParam.getDefaultImg());
        pmsSkuImagesMapper.updateById(model);
    }

    /**
     * sku图片删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSkuImages model = pmsSkuImagesMapper.selectOne(
                new QueryWrapper<PmsSkuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSkuImagesMapper.delete(new QueryWrapper<PmsSkuImages>().eq("id", id));
    }

}
