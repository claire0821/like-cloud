package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSpuImagesService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSpuImagesParam;
import com.mdd.product.vo.PmsSpuImagesListVo;
import com.mdd.product.vo.PmsSpuImagesDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSpuImages;
import com.mdd.product.mapper.PmsSpuImagesMapper;
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
 * spu图片实现类
 */
@Service
public class PmsSpuImagesServiceImpl implements IPmsSpuImagesService {
        
    @Resource
    PmsSpuImagesMapper pmsSpuImagesMapper;

    /**
     * spu图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuImagesListVo>
     */
    @Override
    public PageResult<PmsSpuImagesListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSpuImages> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsSpuImagesMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "like:imgName@img_name:str",
            "=:imgUrl@img_url:str",
            "=:imgSort@img_sort:long",
            "=:defaultImg@default_img:long",
        });

        IPage<PmsSpuImages> iPage = pmsSpuImagesMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSpuImagesListVo> list = new LinkedList<>();
        for(PmsSpuImages item : iPage.getRecords()) {
            PmsSpuImagesListVo vo = new PmsSpuImagesListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu图片详情
     *
     * @param id 主键参数
     * @return PmsSpuImages
     */
    @Override
    public PmsSpuImagesDetailVo detail(Long id) {
        PmsSpuImages model = pmsSpuImagesMapper.selectOne(
                new QueryWrapper<PmsSpuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSpuImagesDetailVo vo = new PmsSpuImagesDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu图片新增
     *
     * @param pmsSpuImagesParam 参数
     */
    @Override
    public void add(PmsSpuImagesParam pmsSpuImagesParam) {
        PmsSpuImages model = new PmsSpuImages();
        model.setSpuId(pmsSpuImagesParam.getSpuId());
        model.setImgName(pmsSpuImagesParam.getImgName());
        model.setImgUrl(pmsSpuImagesParam.getImgUrl());
        model.setImgSort(pmsSpuImagesParam.getImgSort());
        model.setDefaultImg(pmsSpuImagesParam.getDefaultImg());
        pmsSpuImagesMapper.insert(model);
    }

    /**
     * spu图片编辑
     *
     * @param pmsSpuImagesParam 参数
     */
    @Override
    public void edit(PmsSpuImagesParam pmsSpuImagesParam) {
        PmsSpuImages model = pmsSpuImagesMapper.selectOne(
                new QueryWrapper<PmsSpuImages>()
                    .eq("id",  pmsSpuImagesParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsSpuImagesParam.getId());
        model.setSpuId(pmsSpuImagesParam.getSpuId());
        model.setImgName(pmsSpuImagesParam.getImgName());
        model.setImgUrl(pmsSpuImagesParam.getImgUrl());
        model.setImgSort(pmsSpuImagesParam.getImgSort());
        model.setDefaultImg(pmsSpuImagesParam.getDefaultImg());
        pmsSpuImagesMapper.updateById(model);
    }

    /**
     * spu图片删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSpuImages model = pmsSpuImagesMapper.selectOne(
                new QueryWrapper<PmsSpuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSpuImagesMapper.delete(new QueryWrapper<PmsSpuImages>().eq("id", id));
    }

}
