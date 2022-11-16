package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ISpuImagesService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SpuImagesParam;
import com.mdd.product.vo.SpuImagesListVo;
import com.mdd.product.vo.SpuImagesDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SpuImages;
import com.mdd.product.mapper.SpuImagesMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * spu图片实现类
 */
@Service
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper,SpuImages> implements ISpuImagesService {
        
    @Resource
    SpuImagesMapper spuImagesMapper;

    /**
     * spu图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuImagesListVo>
     */
    @Override
    public PageResult<SpuImagesListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SpuImages> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        spuImagesMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "like:imgName@img_name:str",
            "=:imgUrl@img_url:str",
            "=:imgSort@img_sort:int",
            "=:defaultImg@default_img:int",
        });

        IPage<SpuImages> iPage = spuImagesMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SpuImagesListVo> list = new LinkedList<>();
        for(SpuImages item : iPage.getRecords()) {
            SpuImagesListVo vo = new SpuImagesListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu图片详情
     *
     * @param id 主键参数
     * @return SpuImages
     */
    @Override
    public SpuImagesDetailVo detail(Long id) {
        SpuImages model = spuImagesMapper.selectOne(
                new QueryWrapper<SpuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SpuImagesDetailVo vo = new SpuImagesDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu图片新增
     *
     * @param spuImagesParam 参数
     */
    @Override
    public void add(SpuImagesParam spuImagesParam) {
        SpuImages model = new SpuImages();
        model.setSpuId(spuImagesParam.getSpuId());
        model.setImgName(spuImagesParam.getImgName());
        model.setImgUrl(spuImagesParam.getImgUrl());
        model.setImgSort(spuImagesParam.getImgSort());
        model.setDefaultImg(spuImagesParam.getDefaultImg());
        spuImagesMapper.insert(model);
    }

    /**
     * spu图片编辑
     *
     * @param spuImagesParam 参数
     */
    @Override
    public void edit(SpuImagesParam spuImagesParam) {
        SpuImages model = spuImagesMapper.selectOne(
                new QueryWrapper<SpuImages>()
                    .eq("id",  spuImagesParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(spuImagesParam.getId());
        model.setSpuId(spuImagesParam.getSpuId());
        model.setImgName(spuImagesParam.getImgName());
        model.setImgUrl(spuImagesParam.getImgUrl());
        model.setImgSort(spuImagesParam.getImgSort());
        model.setDefaultImg(spuImagesParam.getDefaultImg());
        spuImagesMapper.updateById(model);
    }

    /**
     * spu图片删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SpuImages model = spuImagesMapper.selectOne(
                new QueryWrapper<SpuImages>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        spuImagesMapper.delete(new QueryWrapper<SpuImages>().eq("id", id));
    }

    @Override
    public void saveImages(Long id, List<String> images) {
        if(images == null || images.size() == 0){

        }else{
            List<SpuImages> collect = images.stream().map(img -> {
                SpuImages spuImagesEntity = new SpuImages();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(img);

                return spuImagesEntity;
            }).collect(Collectors.toList());

            this.saveBatch(collect);
        }
    }

}
