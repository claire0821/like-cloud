package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ISkuInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SkuInfoParam;
import com.mdd.product.vo.SkuInfoListVo;
import com.mdd.product.vo.SkuInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SkuInfo;
import com.mdd.product.mapper.SkuInfoMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * sku信息实现类
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper,SkuInfo> implements ISkuInfoService {
        
    @Resource
    SkuInfoMapper skuInfoMapper;

    /**
     * sku信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuInfoListVo>
     */
    @Override
    public PageResult<SkuInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sku_id");
        String key = params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((w)->{
                w.eq("id",key).or().like("spu_name",key);
            });
        }

        final String min = params.get("min");
        final String max = params.get("max");
        if(!StringUtils.isEmpty(min)){
            queryWrapper.ge("price",min);
        }
        if(!StringUtils.isEmpty(max)  ){
            try{
                BigDecimal bigDecimal = new BigDecimal(max);
                if(bigDecimal.compareTo(new BigDecimal("0")) == 1){
                    queryWrapper.le("price",max);
                }
            }catch (Exception e){
            }
        }
        skuInfoMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "like:skuName@sku_name:str",
            "=:skuDesc@sku_desc:str",
            "=:catalogId@catalog_id:long",
            "=:brandId@brand_id:long",
            "=:skuDefaultImg@sku_default_img:str",
            "=:skuTitle@sku_title:str",
            "=:skuSubtitle@sku_subtitle:str",
            "=:saleCount@sale_count:long",
        });

        IPage<SkuInfo> iPage = skuInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SkuInfoListVo> list = new LinkedList<>();
        for(SkuInfo item : iPage.getRecords()) {
            SkuInfoListVo vo = new SkuInfoListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * sku信息详情
     *
     * @param id 主键参数
     * @return SkuInfo
     */
    @Override
    public SkuInfoDetailVo detail(Long id) {
        SkuInfo model = skuInfoMapper.selectOne(
                new QueryWrapper<SkuInfo>()
                    .eq("sku_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SkuInfoDetailVo vo = new SkuInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * sku信息新增
     *
     * @param skuInfoParam 参数
     */
    @Override
    public void add(SkuInfoParam skuInfoParam) {
        SkuInfo model = new SkuInfo();
        model.setSpuId(skuInfoParam.getSpuId());
        model.setSkuName(skuInfoParam.getSkuName());
        model.setSkuDesc(skuInfoParam.getSkuDesc());
        model.setCatalogId(skuInfoParam.getCatalogId());
        model.setBrandId(skuInfoParam.getBrandId());
        model.setSkuDefaultImg(skuInfoParam.getSkuDefaultImg());
        model.setSkuTitle(skuInfoParam.getSkuTitle());
        model.setSkuSubtitle(skuInfoParam.getSkuSubtitle());
        model.setPrice(skuInfoParam.getPrice());
        model.setSaleCount(skuInfoParam.getSaleCount());
        skuInfoMapper.insert(model);
    }

    /**
     * sku信息编辑
     *
     * @param skuInfoParam 参数
     */
    @Override
    public void edit(SkuInfoParam skuInfoParam) {
        SkuInfo model = skuInfoMapper.selectOne(
                new QueryWrapper<SkuInfo>()
                    .eq("sku_id",  skuInfoParam.getSkuId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setSkuId(skuInfoParam.getSkuId());
        model.setSpuId(skuInfoParam.getSpuId());
        model.setSkuName(skuInfoParam.getSkuName());
        model.setSkuDesc(skuInfoParam.getSkuDesc());
        model.setCatalogId(skuInfoParam.getCatalogId());
        model.setBrandId(skuInfoParam.getBrandId());
        model.setSkuDefaultImg(skuInfoParam.getSkuDefaultImg());
        model.setSkuTitle(skuInfoParam.getSkuTitle());
        model.setSkuSubtitle(skuInfoParam.getSkuSubtitle());
        model.setPrice(skuInfoParam.getPrice());
        model.setSaleCount(skuInfoParam.getSaleCount());
        skuInfoMapper.updateById(model);
    }

    /**
     * sku信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SkuInfo model = skuInfoMapper.selectOne(
                new QueryWrapper<SkuInfo>()
                    .eq("sku_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        skuInfoMapper.delete(new QueryWrapper<SkuInfo>().eq("sku_id", id));
    }

    @Override
    public List<SkuInfo> getSkusBySpuId(Long spuId) {
        final List<SkuInfo> spu_id = this.list(new QueryWrapper<SkuInfo>().eq("spu_id", spuId));
        return spu_id;
    }

}
