package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.to.SkuReductionTo;
import com.mdd.common.to.SpuBoundTo;
import com.mdd.product.entity.*;
import com.mdd.product.feign.CouponFeignService;
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
}
