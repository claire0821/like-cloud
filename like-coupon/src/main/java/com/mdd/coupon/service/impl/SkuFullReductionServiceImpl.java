package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.to.SkuReductionTo;
import com.mdd.coupon.entity.MemberPrice;
import com.mdd.coupon.entity.SkuLadder;
import com.mdd.coupon.service.IMemberPriceService;
import com.mdd.coupon.service.ISkuFullReductionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.service.ISkuLadderService;
import com.mdd.coupon.validate.SkuFullReductionParam;
import com.mdd.coupon.vo.SkuFullReductionListVo;
import com.mdd.coupon.vo.SkuFullReductionDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SkuFullReduction;
import com.mdd.coupon.mapper.SkuFullReductionMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品满减信息实现类
 */
@Service
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper,SkuFullReduction> implements ISkuFullReductionService {
        
    @Resource
    SkuFullReductionMapper skuFullReductionMapper;
    @Autowired
    ISkuLadderService iSkuLadderService;
    @Autowired
    IMemberPriceService iMemberPriceService;

    /**
     * 商品满减信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuFullReductionListVo>
     */
    @Override
    public PageResult<SkuFullReductionListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SkuFullReduction> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        skuFullReductionMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:fullPrice@full_price:str",
            "=:reducePrice@reduce_price:str",
            "=:addOther@add_other:int",
        });

        IPage<SkuFullReduction> iPage = skuFullReductionMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SkuFullReductionListVo> list = new LinkedList<>();
        for(SkuFullReduction item : iPage.getRecords()) {
            SkuFullReductionListVo vo = new SkuFullReductionListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品满减信息详情
     *
     * @param id 主键参数
     * @return SkuFullReduction
     */
    @Override
    public SkuFullReductionDetailVo detail(Long id) {
        SkuFullReduction model = skuFullReductionMapper.selectOne(
                new QueryWrapper<SkuFullReduction>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SkuFullReductionDetailVo vo = new SkuFullReductionDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品满减信息新增
     *
     * @param skuFullReductionParam 参数
     */
    @Override
    public void add(SkuFullReductionParam skuFullReductionParam) {
        SkuFullReduction model = new SkuFullReduction();
        model.setSkuId(skuFullReductionParam.getSkuId());
        model.setFullPrice(skuFullReductionParam.getFullPrice());
        model.setReducePrice(skuFullReductionParam.getReducePrice());
        model.setAddOther(skuFullReductionParam.getAddOther());
        skuFullReductionMapper.insert(model);
    }

    /**
     * 商品满减信息编辑
     *
     * @param skuFullReductionParam 参数
     */
    @Override
    public void edit(SkuFullReductionParam skuFullReductionParam) {
        SkuFullReduction model = skuFullReductionMapper.selectOne(
                new QueryWrapper<SkuFullReduction>()
                    .eq("id",  skuFullReductionParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(skuFullReductionParam.getId());
        model.setSkuId(skuFullReductionParam.getSkuId());
        model.setFullPrice(skuFullReductionParam.getFullPrice());
        model.setReducePrice(skuFullReductionParam.getReducePrice());
        model.setAddOther(skuFullReductionParam.getAddOther());
        skuFullReductionMapper.updateById(model);
    }

    /**
     * 商品满减信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SkuFullReduction model = skuFullReductionMapper.selectOne(
                new QueryWrapper<SkuFullReduction>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        skuFullReductionMapper.delete(new QueryWrapper<SkuFullReduction>().eq("id", id));
    }

    @Override
    public void saveSkuReduction(SkuReductionTo reductionTo) {
//1、// //5.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
        //sms_sku_ladder
        SkuLadder skuLadderEntity = new SkuLadder();
        skuLadderEntity.setSkuId(reductionTo.getSkuId());
        skuLadderEntity.setFullCount(reductionTo.getFullCount());
        skuLadderEntity.setDiscount(reductionTo.getDiscount());
        skuLadderEntity.setAddOther(reductionTo.getCountStatus());
        if(reductionTo.getFullCount() > 0){
            iSkuLadderService.save(skuLadderEntity);
        }

        //2、sms_sku_full_reduction
        SkuFullReduction reductionEntity = new SkuFullReduction();
        BeanUtils.copyProperties(reductionTo,reductionEntity);
        if(reductionEntity.getFullPrice().compareTo(new BigDecimal("0"))==1){
            this.save(reductionEntity);
        }

        //3、sms_member_price
        List<com.mdd.common.to.MemberPrice> memberPrice = reductionTo.getMemberPrice();

        List<MemberPrice> collect = memberPrice.stream().map(item -> {
            MemberPrice priceEntity = new MemberPrice();
            priceEntity.setSkuId(reductionTo.getSkuId());
            priceEntity.setMemberLevelId(item.getId());
            priceEntity.setMemberLevelName(item.getName());
            priceEntity.setMemberPrice(item.getPrice());
            priceEntity.setAddOther(1);
            return priceEntity;
        }).filter(item->{
            return item.getMemberPrice().compareTo(new BigDecimal("0")) == 1;
        }).collect(Collectors.toList());

        iMemberPriceService.saveBatch(collect);
    }

}
