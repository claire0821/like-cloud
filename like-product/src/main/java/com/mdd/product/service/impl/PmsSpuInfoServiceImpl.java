package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSpuInfoService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSpuInfoParam;
import com.mdd.product.vo.PmsSpuInfoListVo;
import com.mdd.product.vo.PmsSpuInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSpuInfo;
import com.mdd.product.mapper.PmsSpuInfoMapper;
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
 * spu信息实现类
 */
@Service
public class PmsSpuInfoServiceImpl implements IPmsSpuInfoService {
        
    @Resource
    PmsSpuInfoMapper pmsSpuInfoMapper;

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuInfoListVo>
     */
    @Override
    public PageResult<PmsSpuInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsSpuInfoMapper.setSearch(queryWrapper, params, new String[]{
            "like:spuName@spu_name:str",
            "=:spuDescription@spu_description:str",
            "=:catalogId@catalog_id:long",
            "=:brandId@brand_id:long",
            "=:weight:str",
            "=:publishStatus@publish_status:long",
        });

        IPage<PmsSpuInfo> iPage = pmsSpuInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSpuInfoListVo> list = new LinkedList<>();
        for(PmsSpuInfo item : iPage.getRecords()) {
            PmsSpuInfoListVo vo = new PmsSpuInfoListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
//            vo.setUpdateTime(TimeUtil.timestampToDate(item.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu信息详情
     *
     * @param id 主键参数
     * @return PmsSpuInfo
     */
    @Override
    public PmsSpuInfoDetailVo detail(Long id) {
        PmsSpuInfo model = pmsSpuInfoMapper.selectOne(
                new QueryWrapper<PmsSpuInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSpuInfoDetailVo vo = new PmsSpuInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu信息新增
     *
     * @param pmsSpuInfoParam 参数
     */
    @Override
    public void add(PmsSpuInfoParam pmsSpuInfoParam) {
        PmsSpuInfo model = new PmsSpuInfo();
        model.setSpuName(pmsSpuInfoParam.getSpuName());
        model.setSpuDescription(pmsSpuInfoParam.getSpuDescription());
        model.setCatalogId(pmsSpuInfoParam.getCatalogId());
        model.setBrandId(pmsSpuInfoParam.getBrandId());
        model.setWeight(pmsSpuInfoParam.getWeight());
        model.setPublishStatus(pmsSpuInfoParam.getPublishStatus());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
//        model.setUpdateTime(System.currentTimeMillis() / 1000);
        pmsSpuInfoMapper.insert(model);
    }

    /**
     * spu信息编辑
     *
     * @param pmsSpuInfoParam 参数
     */
    @Override
    public void edit(PmsSpuInfoParam pmsSpuInfoParam) {
        PmsSpuInfo model = pmsSpuInfoMapper.selectOne(
                new QueryWrapper<PmsSpuInfo>()
                    .eq("id",  pmsSpuInfoParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsSpuInfoParam.getId());
        model.setSpuName(pmsSpuInfoParam.getSpuName());
        model.setSpuDescription(pmsSpuInfoParam.getSpuDescription());
        model.setCatalogId(pmsSpuInfoParam.getCatalogId());
        model.setBrandId(pmsSpuInfoParam.getBrandId());
        model.setWeight(pmsSpuInfoParam.getWeight());
        model.setPublishStatus(pmsSpuInfoParam.getPublishStatus());
//        model.setUpdateTime(System.currentTimeMillis() / 1000);
        pmsSpuInfoMapper.updateById(model);
    }

    /**
     * spu信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSpuInfo model = pmsSpuInfoMapper.selectOne(
                new QueryWrapper<PmsSpuInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSpuInfoMapper.delete(new QueryWrapper<PmsSpuInfo>().eq("id", id));
    }

}
