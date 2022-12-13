package com.mdd.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.common.config.ThreadPoolConfig;
import com.mdd.product.entity.SkuImages;
import com.mdd.product.entity.SkuSaleAttrValue;
import com.mdd.product.entity.SpuInfoDesc;
import com.mdd.product.service.*;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SkuInfoParam;
import com.mdd.product.vo.*;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SkuInfo;
import com.mdd.product.mapper.SkuInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * sku信息实现类
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper,SkuInfo> implements ISkuInfoService {
        
    @Resource
    SkuInfoMapper skuInfoMapper;
    @Autowired
    Executor executor;
    @Autowired
    ISkuSaleAttrValueService iSkuSaleAttrValueService;
    @Autowired
    ISkuImagesService iSkuImagesService;
    @Autowired
    ISpuInfoDescService iSpuInfoDescService;
    @Autowired
    IAttrGroupService iAttrGroupService;

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

    @Override
    public ProductDetaliVo productDetail(Long skuId) {
        ProductDetaliVo productDetaliVo = new ProductDetaliVo();

//        CompletableFuture<SkuInfo> infoFuture = CompletableFuture.supplyAsync(() -> {
//            //1、sku基本信息的获取  pms_sku_info
//            SkuInfo info = this.getById(skuId);
//            productDetaliVo.setInfo(info);
//            productDetaliVo.setBrandId(info.getBrandId());
//            productDetaliVo.setCatalogId(info.getCatalogId());
//            productDetaliVo.setPrice(info.getPrice());
//            productDetaliVo.setSkuId(info.getSkuId());
//            productDetaliVo.setSkuName(info.getSkuName());
//            productDetaliVo.setSkuTitle(info.getSkuTitle());
//            productDetaliVo.setSpuId(info.getSpuId());
//            return info;
//        }, executor);
//
//
//        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
//            //3、获取spu的销售属性组合
//            List<SkuItemSaleAttrVo> saleAttrVos = iSkuSaleAttrValueService.getSaleAttrBySpuId(res.getSpuId());
//            productDetaliVo.setSaleAttr(saleAttrVos);
//        }, executor);
//
//
//        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((res) -> {
//            //4、获取spu的介绍    pms_spu_info_desc
//            SpuInfoDesc spuInfoDescEntity = iSpuInfoDescService.getById(res.getSpuId());
//            productDetaliVo.setDesc(spuInfoDescEntity);
//        }, executor);
//
//
//        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync((res) -> {
//            //5、获取spu的规格参数信息
//            List<SpuItemAttrGroupVo> attrGroupVos = iAttrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
//            productDetaliVo.setGroupAttrs(attrGroupVos);
//        }, executor);
//
//
//        // Long spuId = info.getSpuId();
//        // Long catalogId = info.getCatalogId();
//
//        //2、sku的图片信息    pms_sku_images
//        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
//            List<SkuImages> imagesEntities = iSkuImagesService.getImagesBySkuId(skuId);
//            productDetaliVo.setImages(imagesEntities);
//        }, executor);
//
//        //CompletableFuture<Void> seckillFuture = CompletableFuture.runAsync(() -> {
//            //3、远程调用查询当前sku是否参与秒杀优惠活动
////            R skuSeckilInfo = seckillFeignService.getSkuSeckilInfo(skuId);
////            if (skuSeckilInfo.getCode() == 0) {
////                //查询成功
////                SeckillSkuVo seckilInfoData = skuSeckilInfo.getData("data", new TypeReference<SeckillSkuVo>() {
////                });
////                skuItemVo.setSeckillSkuVo(seckilInfoData);
////
////                if (seckilInfoData != null) {
////                    long currentTime = System.currentTimeMillis();
////                    if (currentTime > seckilInfoData.getEndTime()) {
////                        skuItemVo.setSeckillSkuVo(null);
////                    }
////                }
////            }
//        //}, executor);
//
//
//        //等到所有任务都完成
//        try {
//            CompletableFuture.allOf(saleAttrFuture,descFuture,baseAttrFuture,imageFuture).get();
//            //CompletableFuture.allOf(saleAttrFuture,descFuture,baseAttrFuture,imageFuture,seckillFuture).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        return productDetaliVo;
    }

    @Override
    public ProductDetaliSkuVo getDetial(Long skuId) {
        ProductDetaliSkuVo productDetaliSkuVo = new ProductDetaliSkuVo();
        CompletableFuture<Void> skuInfoFuture = CompletableFuture.runAsync(() -> {
            final SkuInfo skuInfo = this.getById(skuId);
            if(skuInfo == null) {

            }

            productDetaliSkuVo.setSkuId(skuInfo.getSkuId());
            productDetaliSkuVo.setName(skuInfo.getSkuName());
            productDetaliSkuVo.setDesc(skuInfo.getSkuDesc());
            productDetaliSkuVo.setImg(skuInfo.getSkuDefaultImg());
            productDetaliSkuVo.setSubtitle(skuInfo.getSkuSubtitle());
            productDetaliSkuVo.setTitle(skuInfo.getSkuTitle());
            productDetaliSkuVo.setPrice(skuInfo.getPrice());

            final List<SkuSaleAttrValue> skuSaleAttrValues = iSkuSaleAttrValueService.list(new QueryWrapper<SkuSaleAttrValue>().eq("sku_id", skuInfo.getSkuId()));
            //sku对应的销售属性

            List<com.mdd.product.vo.Attr> attrs = new ArrayList<>();
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValues) {
                com.mdd.product.vo.Attr attr = new com.mdd.product.vo.Attr();
                attr.setAttrId(skuSaleAttrValue.getAttrId());
                attr.setAttrName(skuSaleAttrValue.getAttrName());
                attr.setAttrValue(skuSaleAttrValue.getAttrValue());
                attrs.add(attr);
            }

            final List<String> collect = attrs.stream().map(com.mdd.product.vo.Attr::getAttrValue).collect(Collectors.toList());
            productDetaliSkuVo.setSaleValueStr(String.join(",",collect));
            productDetaliSkuVo.setSaleAttr(attrs);
            //TODO 库存 会员价 活动价 销量 浏览量
            productDetaliSkuVo.setStock(10L);
            productDetaliSkuVo.setMemberPrice(productDetaliSkuVo.getPrice());
            productDetaliSkuVo.setActivityPrice(productDetaliSkuVo.getPrice());
            productDetaliSkuVo.setSaleCount(100L);
            productDetaliSkuVo.setClickCount(100L);
        }, executor);

        //TODO 库存
        //等到所有任务都完成
        try {
            CompletableFuture.allOf(skuInfoFuture).get();
            //CompletableFuture.allOf(saleAttrFuture,descFuture,baseAttrFuture,imageFuture,seckillFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return productDetaliSkuVo;
    }

}
