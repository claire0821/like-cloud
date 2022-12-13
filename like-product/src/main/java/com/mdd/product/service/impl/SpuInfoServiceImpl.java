package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.constant.ProductConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.es.SkuEsModel;
import com.mdd.common.to.SkuHasStockVo;
import com.mdd.common.to.SkuReductionTo;
import com.mdd.common.to.SpuBoundTo;
import com.mdd.product.entity.*;
import com.mdd.product.entity.Attr;
import com.mdd.product.feign.CouponFeignService;
import com.mdd.product.feign.SearchFeignService;
import com.mdd.product.feign.WareFeignService;
import com.mdd.product.service.*;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SpuInfoParam;
import com.mdd.product.vo.*;
import com.mdd.common.core.PageResult;
import com.mdd.product.mapper.SpuInfoMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * spu信息实现类
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper,SpuInfo> implements ISpuInfoService {

    @Resource
    SpuInfoMapper spuInfoMapper;

    @Autowired
    ISpuInfoDescService iSpuInfoDescService;
    @Autowired
    ISpuImagesService iSpuImagesService;
    @Autowired
    IAttrService iAttrService;
    @Autowired
    IProductAttrValueService iProductAttrValueService;
    @Autowired
    ISkuInfoService iSkuInfoService;
    @Autowired
    ISkuImagesService iSkuImagesService;
    @Autowired
    ISkuSaleAttrValueService iSkuSaleAttrValueService;
    @Autowired
    CouponFeignService couponFeignService;
    @Autowired
    WareFeignService wareFeignService;
    @Autowired
    IBrandService iBrandService;
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    Executor executor;
    @Autowired
    IAttrGroupService iAttrGroupService;

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuInfoListVo>
     */
    @Override
    public PageResult<SpuInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        String key = params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((w)->{
                w.eq("id",key).or().like("spu_name",key);
            });
        }
        spuInfoMapper.setSearch(queryWrapper, params, new String[]{
                "like:spuName@spu_name:str",
                "=:spuDescription@spu_description:str",
                "=:catalogId@catalog_id:long",
                "=:brandId@brand_id:long",
                "=:weight:str",
                "=:publishStatus@publish_status:int",
        });

        IPage<SpuInfo> iPage = spuInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SpuInfoListVo> list = new LinkedList<>();
        for(SpuInfo item : iPage.getRecords()) {
            SpuInfoListVo vo = new SpuInfoListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            vo.setUpdateTime(simpleDateFormat.format(item.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu信息详情
     *
     * @param id 主键参数
     * @return SpuInfo
     */
    @Override
    public SpuInfoDetailVo detail(Long id) {
        SpuInfo model = spuInfoMapper.selectOne(
                new QueryWrapper<SpuInfo>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SpuInfoDetailVo vo = new SpuInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu信息新增
     *
     * @param spuInfoParam 参数
     */
    @Override
    public void add(SpuInfoParam spuInfoParam) {
        SpuInfo model = new SpuInfo();
        model.setSpuName(spuInfoParam.getSpuName());
        model.setSpuDescription(spuInfoParam.getSpuDescription());
        model.setCatalogId(spuInfoParam.getCatalogId());
        model.setBrandId(spuInfoParam.getBrandId());
        model.setWeight(spuInfoParam.getWeight());
        model.setPublishStatus(spuInfoParam.getPublishStatus());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        spuInfoMapper.insert(model);
    }

    /**
     * spu信息编辑
     *
     * @param spuInfoParam 参数
     */
    @Override
    public void edit(SpuInfoParam spuInfoParam) {
        SpuInfo model = spuInfoMapper.selectOne(
                new QueryWrapper<SpuInfo>()
                        .eq("id",  spuInfoParam.getId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(spuInfoParam.getId());
        model.setSpuName(spuInfoParam.getSpuName());
        model.setSpuDescription(spuInfoParam.getSpuDescription());
        model.setCatalogId(spuInfoParam.getCatalogId());
        model.setBrandId(spuInfoParam.getBrandId());
        model.setWeight(spuInfoParam.getWeight());
        model.setPublishStatus(spuInfoParam.getPublishStatus());
        model.setUpdateTime(new Date());
        model.setCreateTime(new Date());
        //TODO代码生成时间类型
        spuInfoMapper.updateById(model);
    }

    /**
     * spu信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SpuInfo model = spuInfoMapper.selectOne(
                new QueryWrapper<SpuInfo>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        spuInfoMapper.delete(new QueryWrapper<SpuInfo>().eq("id", id));
    }

    //TODO 高级部分完善失败策略
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        //1、保存spu基本信息 pms_spu_info
        SpuInfo infoEntity = new SpuInfo();
        BeanUtils.copyProperties(vo,infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);

        //2、保存Spu的描述图片 pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDesc descEntity = new SpuInfoDesc();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(String.join(",",decript));
        iSpuInfoDescService.save(descEntity);

        //3、保存spu的图片集 pms_spu_images
        List<String> images = vo.getImages();
        iSpuImagesService.saveImages(infoEntity.getId(),images);

        //4、保存spu的规格参数;pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValue> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValue valueEntity = new ProductAttrValue();
            valueEntity.setAttrId(attr.getAttrId());
            Attr id = iAttrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(infoEntity.getId());

            return valueEntity;
        }).collect(Collectors.toList());
        iProductAttrValueService.saveProductAttr(collect);

        //5、保存spu的积分信息；mall_sms->sms_spu_bounds
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(infoEntity.getId());
        final LinkedHashMap map = (LinkedHashMap) couponFeignService.saveSpuBounds(spuBoundTo);
        final Integer code = (Integer) map.get("code");
        if(code != HttpEnum.SUCCESS.getCode()){
            log.error("远程保存spu积分信息失败" + map.get("msg"));
        }

        //6、保存当前spu对应的所有sku信息；
        List<Skus> skus = vo.getSkus();
        if(skus == null || skus.isEmpty()) {

        }
        skus.forEach(item -> {
            String defaultImg = "";
            for (Images image : item.getImages()) {
                if(image.getDefaultImg() == 1){
                    defaultImg = image.getImgUrl();
                }
            }

            //6.1）、sku的基本信息；pms_sku_info
            SkuInfo skuInfoEntity = new SkuInfo();
            BeanUtils.copyProperties(item,skuInfoEntity);
            skuInfoEntity.setBrandId(infoEntity.getBrandId());
            skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSpuId(infoEntity.getId());
            skuInfoEntity.setSkuDefaultImg(defaultImg);
            iSkuInfoService.save(skuInfoEntity);

            //6.2）、sku的图片信息；pms_sku_image
            Long skuId = skuInfoEntity.getSkuId();
            List<SkuImages> imagesEntities = item.getImages().stream().map(img -> {
                SkuImages skuImagesEntity = new SkuImages();
                skuImagesEntity.setSkuId(skuId);
                skuImagesEntity.setImgUrl(img.getImgUrl());
                skuImagesEntity.setDefaultImg(img.getDefaultImg());
                return skuImagesEntity;
            }).filter(entity->{
                //返回true就是需要，false就是剔除
                return !StringUtils.isEmpty(entity.getImgUrl());
            }).collect(Collectors.toList());
            iSkuImagesService.saveBatch(imagesEntities);

            //6.3）、sku的销售属性信息：pms_sku_sale_attr_value
            List<Attr> attr = item.getAttr();
            List<SkuSaleAttrValue> skuSaleAttrValueEntities = attr.stream().map(a -> {
                SkuSaleAttrValue attrValueEntity = new SkuSaleAttrValue();
                BeanUtils.copyProperties(a, attrValueEntity);
                attrValueEntity.setSkuId(skuId);

                return attrValueEntity;
            }).collect(Collectors.toList());
            iSkuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

            //6.4）、sku的优惠、满减等信息；mall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
            SkuReductionTo skuReductionTo = new SkuReductionTo();
            BeanUtils.copyProperties(item,skuReductionTo);
            skuReductionTo.setSkuId(skuId);
            if(skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1){
                final LinkedHashMap map1 = (LinkedHashMap) couponFeignService.saveSkuReduction(skuReductionTo);
                final Integer code1 = (Integer) map1.get("code");
                if(code1 != HttpEnum.SUCCESS.getCode()){
                    log.error("远程保存sku优惠信息失败");
                }
            }
        });
    }

    @Override
    public void saveBaseSpuInfo(SpuInfo spuInfo) {
        this.baseMapper.insert(spuInfo);
    }

    //TODO 异常处理
    //@GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void up(Long spuId) {
        //1、查出当前spuId对应的所有sku信息,品牌的名字
        List<SkuInfo> skuInfoEntities = iSkuInfoService.getSkusBySpuId(spuId);

        //4、查出当前sku的所有可以被用来检索的规格属性
        List<ProductAttrValue> baseAttrs = iProductAttrValueService.baseAttrListforspu(spuId);
        final List<Long> attrIds = baseAttrs.stream().map(attr -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        List<Long> searchAttrIds = iAttrService.selectSearchAttrs(attrIds);
        //转换为Set集合
        final Set<Long> idSet = searchAttrIds.stream().collect(Collectors.toSet());

        final List<SkuEsModel.Attrs> attrsList = baseAttrs.stream().filter(item -> {
            return idSet.contains(item.getAttrId());
        }).map(item -> {
            SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
            BeanUtils.copyProperties(item, attrs);
            return attrs;
        }).collect(Collectors.toList());

        final List<Long> skuIdList = skuInfoEntities.stream()
                .map(SkuInfo::getSkuId)
                .collect(Collectors.toList());

        //TODO 1、发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try{
            final LinkedHashMap skuHasStock = (LinkedHashMap) wareFeignService.getSkuHasStock(skuIdList);
            final Integer code = (Integer) skuHasStock.get("code");
            if(code == HttpEnum.SUCCESS.getCode()) {
                stockMap = (Map<Long, Boolean>) skuHasStock.get("data");
            }
            final List data = (List) skuHasStock.get("data");
            stockMap = (Map<Long, Boolean>) data.stream()
                    .collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));
        } catch (Exception ex) {

        }

        //2、封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        final List<SkuEsModel> collect = skuInfoEntities.stream().map(sku -> {
            //组装需要的数据
            SkuEsModel esModel = new SkuEsModel();
            esModel.setSkuPrice(sku.getPrice());
            esModel.setSkuImg(sku.getSkuDefaultImg());
            //设置库存信息
            if (finalStockMap == null) {
                esModel.setHasStock(true);
            } else {
                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
            }

            //TODO 2、热度评分。0
            esModel.setHotScore(0L);

            //3、查询品牌和分类的名字信息
            Brand brandEntity = iBrandService.getById(sku.getBrandId());
            esModel.setBrandName(brandEntity.getName());
            esModel.setBrandId(brandEntity.getBrandId());
            esModel.setBrandImg(brandEntity.getLogo());

            Category categoryEntity = iCategoryService.getById(sku.getCatalogId());
            esModel.setCategoryId(categoryEntity.getCatId());
            esModel.setCategoryName(categoryEntity.getName());

            //设置检索属性
            esModel.setAttrs(attrsList);
            BeanUtils.copyProperties(sku, esModel);
            return esModel;
        }).collect(Collectors.toList());

        //TODO 5、将数据发给es进行保存：gulimall-search
        final LinkedHashMap res = (LinkedHashMap) searchFeignService.productStatusUp(collect);
        final Integer code = (Integer) res.get("code");
        if (code == HttpEnum.SUCCESS.getCode()) {
            //远程调用成功
            //TODO 6、修改当前spu的状态
            this.update(new UpdateWrapper<SpuInfo>().eq("id",spuId).set("publish_status",ProductConstant.ProductStatusEnum.SPU_UP.getCode()));
        } else {
            //远程调用失败
            //TODO 7、重复调用？接口幂等性:重试机制
        }
    }


    @Override
    public ProductDetaliVo productDetail(Long spuId, Long skuId) {
        ProductDetaliVo productDetaliVo = new ProductDetaliVo();

        //spu基本信息
        CompletableFuture<SpuInfo> spuFuture = CompletableFuture.supplyAsync(() -> {
            final SpuInfo byId = this.getById(spuId);
            productDetaliVo.setSpuId(spuId);
            productDetaliVo.setTitle(byId.getSpuName());
            return byId;
        }, executor);

        //2、spu的图片信息    pms_sku_images
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            final List<SpuImages> imagesSort = iSpuImagesService.getImagesSort(spuId);
            if(imagesSort != null && imagesSort.size() > 0) {
                productDetaliVo.setImages(imagesSort.stream().map(SpuImages::getImgUrl).collect(Collectors.toList()));
//                productDetaliVo.setImages(imagesSort);
            }
        }, executor);

        //sku集合
        List<ProductDetaliSkuVo> productDetaliSkuVos = new ArrayList<>();

        CompletableFuture<Void> skuInfoFuture = CompletableFuture.runAsync(() -> {
            final List<SkuInfo> skusBySpuId = iSkuInfoService.getSkusBySpuId(spuId);
            if(skusBySpuId != null && skusBySpuId.size() > 0) {
                for (SkuInfo skuInfo : skusBySpuId) {
                    ProductDetaliSkuVo productDetaliSkuVo = new ProductDetaliSkuVo();
                    productDetaliSkuVo.setSkuId(skuInfo.getSkuId());
                    productDetaliSkuVo.setName(skuInfo.getSkuName());
                    productDetaliSkuVo.setDesc(skuInfo.getSkuDesc());
                    productDetaliSkuVo.setImg(skuInfo.getSkuDefaultImg());
                    productDetaliSkuVo.setSubtitle(skuInfo.getSkuSubtitle());
                    productDetaliSkuVo.setTitle(skuInfo.getSkuTitle());
                    productDetaliSkuVo.setPrice(skuInfo.getPrice());
                    productDetaliSkuVos.add(productDetaliSkuVo);

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
                    if(skuId.equals(skuInfo.getSkuId())) {
                        productDetaliVo.setCheckedProduct(productDetaliSkuVo);
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
                }
            }

            productDetaliVo.setSkuInfos(productDetaliSkuVos);
            productDetaliVo.setTotalSaleCount(productDetaliSkuVos.stream().mapToLong(ProductDetaliSkuVo::getSaleCount).sum());
            productDetaliVo.setTotalClickCount(productDetaliSkuVos.stream().mapToLong(ProductDetaliSkuVo::getClickCount).sum());
        }, executor);
        //TODO sku秒杀价 团购价 会员价
        //获取spu的销售属性组合
        CompletableFuture<Void> saleAttrFuture = CompletableFuture.runAsync(() -> {
            List<SkuItemSaleAttrVo> saleAttrVos = iSkuSaleAttrValueService.getSaleAttrBySpuId(spuId);
            productDetaliVo.setSaleAttr(saleAttrVos);
        }, executor);

        //获取spu的规格参数信息
        CompletableFuture<Void> baseAttrFuture = spuFuture.thenAcceptAsync((res) -> {
            List<SpuItemAttrGroupVo> attrGroupVos = iAttrGroupService.getAttrGroupWithAttrsBySpuId(spuId, res.getCatalogId());
            productDetaliVo.setGroupAttrs(attrGroupVos);
        }, executor);


        // Long spuId = info.getSpuId();
        // Long catalogId = info.getCatalogId();



        //CompletableFuture<Void> seckillFuture = CompletableFuture.runAsync(() -> {
        //3、远程调用查询当前sku是否参与秒杀优惠活动
//            R skuSeckilInfo = seckillFeignService.getSkuSeckilInfo(skuId);
//            if (skuSeckilInfo.getCode() == 0) {
//                //查询成功
//                SeckillSkuVo seckilInfoData = skuSeckilInfo.getData("data", new TypeReference<SeckillSkuVo>() {
//                });
//                skuItemVo.setSeckillSkuVo(seckilInfoData);
//
//                if (seckilInfoData != null) {
//                    long currentTime = System.currentTimeMillis();
//                    if (currentTime > seckilInfoData.getEndTime()) {
//                        skuItemVo.setSeckillSkuVo(null);
//                    }
//                }
//            }
        //}, executor);


        //等到所有任务都完成
        try {
            CompletableFuture.allOf(spuFuture,imageFuture,skuInfoFuture,saleAttrFuture,baseAttrFuture).get();
            //CompletableFuture.allOf(saleAttrFuture,descFuture,baseAttrFuture,imageFuture,seckillFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //TODO 活动类型
        productDetaliVo.setActivityType(3);
        return productDetaliVo;
    }
}
