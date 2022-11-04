package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSkuInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSkuInfoParam;
import com.mdd.product.vo.PmsSkuInfoListVo;
import com.mdd.product.vo.PmsSkuInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSkuInfo;
import com.mdd.product.mapper.PmsSkuInfoMapper;
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
 * sku信息实现类
 */
@Service
public class PmsSkuInfoServiceImpl implements IPmsSkuInfoService {
        
    @Resource
    PmsSkuInfoMapper pmsSkuInfoMapper;

    /**
     * sku信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSkuInfoListVo>
     */
    @Override
    public PageResult<PmsSkuInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSkuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sku_id");

        pmsSkuInfoMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "like:skuName@sku_name:str",
            "=:skuDesc@sku_desc:str",
            "=:catalogId@catalog_id:long",
            "=:brandId@brand_id:long",
            "=:skuDefaultImg@sku_default_img:str",
            "=:skuTitle@sku_title:str",
            "=:skuSubtitle@sku_subtitle:str",
            "=:price:str",
            "=:saleCount@sale_count:long",
        });

        IPage<PmsSkuInfo> iPage = pmsSkuInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSkuInfoListVo> list = new LinkedList<>();
        for(PmsSkuInfo item : iPage.getRecords()) {
            PmsSkuInfoListVo vo = new PmsSkuInfoListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * sku信息详情
     *
     * @param id 主键参数
     * @return PmsSkuInfo
     */
    @Override
    public PmsSkuInfoDetailVo detail(Long id) {
        PmsSkuInfo model = pmsSkuInfoMapper.selectOne(
                new QueryWrapper<PmsSkuInfo>()
                    .eq("sku_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSkuInfoDetailVo vo = new PmsSkuInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * sku信息新增
     *
     * @param pmsSkuInfoParam 参数
     */
    @Override
    public void add(PmsSkuInfoParam pmsSkuInfoParam) {
        PmsSkuInfo model = new PmsSkuInfo();
        model.setSpuId(pmsSkuInfoParam.getSpuId());
        model.setSkuName(pmsSkuInfoParam.getSkuName());
        model.setSkuDesc(pmsSkuInfoParam.getSkuDesc());
        model.setCatalogId(pmsSkuInfoParam.getCatalogId());
        model.setBrandId(pmsSkuInfoParam.getBrandId());
        model.setSkuDefaultImg(pmsSkuInfoParam.getSkuDefaultImg());
        model.setSkuTitle(pmsSkuInfoParam.getSkuTitle());
        model.setSkuSubtitle(pmsSkuInfoParam.getSkuSubtitle());
        model.setPrice(pmsSkuInfoParam.getPrice());
        model.setSaleCount(pmsSkuInfoParam.getSaleCount());
        pmsSkuInfoMapper.insert(model);
    }

    /**
     * sku信息编辑
     *
     * @param pmsSkuInfoParam 参数
     */
    @Override
    public void edit(PmsSkuInfoParam pmsSkuInfoParam) {
        PmsSkuInfo model = pmsSkuInfoMapper.selectOne(
                new QueryWrapper<PmsSkuInfo>()
                    .eq("sku_id",  pmsSkuInfoParam.getSkuId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setSkuId(pmsSkuInfoParam.getSkuId());
        model.setSpuId(pmsSkuInfoParam.getSpuId());
        model.setSkuName(pmsSkuInfoParam.getSkuName());
        model.setSkuDesc(pmsSkuInfoParam.getSkuDesc());
        model.setCatalogId(pmsSkuInfoParam.getCatalogId());
        model.setBrandId(pmsSkuInfoParam.getBrandId());
        model.setSkuDefaultImg(pmsSkuInfoParam.getSkuDefaultImg());
        model.setSkuTitle(pmsSkuInfoParam.getSkuTitle());
        model.setSkuSubtitle(pmsSkuInfoParam.getSkuSubtitle());
        model.setPrice(pmsSkuInfoParam.getPrice());
        model.setSaleCount(pmsSkuInfoParam.getSaleCount());
        pmsSkuInfoMapper.updateById(model);
    }

    /**
     * sku信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSkuInfo model = pmsSkuInfoMapper.selectOne(
                new QueryWrapper<PmsSkuInfo>()
                    .eq("sku_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSkuInfoMapper.delete(new QueryWrapper<PmsSkuInfo>().eq("sku_id", id));
    }

}
