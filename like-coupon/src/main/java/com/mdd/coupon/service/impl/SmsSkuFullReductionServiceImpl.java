package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsSkuFullReductionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSkuFullReductionParam;
import com.mdd.coupon.vo.SmsSkuFullReductionListVo;
import com.mdd.coupon.vo.SmsSkuFullReductionDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSkuFullReduction;
import com.mdd.coupon.mapper.SmsSkuFullReductionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品满减信息实现类
 */
@Service
public class SmsSkuFullReductionServiceImpl implements ISmsSkuFullReductionService {
        
    @Resource
    SmsSkuFullReductionMapper smsSkuFullReductionMapper;

    /**
     * 商品满减信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSkuFullReductionListVo>
     */
    @Override
    public PageResult<SmsSkuFullReductionListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSkuFullReduction> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSkuFullReductionMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:fullPrice@full_price:str",
            "=:reducePrice@reduce_price:str",
            "=:addOther@add_other:int",
        });

        IPage<SmsSkuFullReduction> iPage = smsSkuFullReductionMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSkuFullReductionListVo> list = new LinkedList<>();
        for(SmsSkuFullReduction item : iPage.getRecords()) {
            SmsSkuFullReductionListVo vo = new SmsSkuFullReductionListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品满减信息详情
     *
     * @param id 主键参数
     * @return SmsSkuFullReduction
     */
    @Override
    public SmsSkuFullReductionDetailVo detail(Long id) {
        SmsSkuFullReduction model = smsSkuFullReductionMapper.selectOne(
                new QueryWrapper<SmsSkuFullReduction>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSkuFullReductionDetailVo vo = new SmsSkuFullReductionDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品满减信息新增
     *
     * @param smsSkuFullReductionParam 参数
     */
    @Override
    public void add(SmsSkuFullReductionParam smsSkuFullReductionParam) {
        SmsSkuFullReduction model = new SmsSkuFullReduction();
        model.setSkuId(smsSkuFullReductionParam.getSkuId());
        model.setFullPrice(smsSkuFullReductionParam.getFullPrice());
        model.setReducePrice(smsSkuFullReductionParam.getReducePrice());
        model.setAddOther(smsSkuFullReductionParam.getAddOther());
        smsSkuFullReductionMapper.insert(model);
    }

    /**
     * 商品满减信息编辑
     *
     * @param smsSkuFullReductionParam 参数
     */
    @Override
    public void edit(SmsSkuFullReductionParam smsSkuFullReductionParam) {
        SmsSkuFullReduction model = smsSkuFullReductionMapper.selectOne(
                new QueryWrapper<SmsSkuFullReduction>()
                    .eq("id",  smsSkuFullReductionParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSkuFullReductionParam.getId());
        model.setSkuId(smsSkuFullReductionParam.getSkuId());
        model.setFullPrice(smsSkuFullReductionParam.getFullPrice());
        model.setReducePrice(smsSkuFullReductionParam.getReducePrice());
        model.setAddOther(smsSkuFullReductionParam.getAddOther());
        smsSkuFullReductionMapper.updateById(model);
    }

    /**
     * 商品满减信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSkuFullReduction model = smsSkuFullReductionMapper.selectOne(
                new QueryWrapper<SmsSkuFullReduction>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSkuFullReductionMapper.delete(new QueryWrapper<SmsSkuFullReduction>().eq("id", id));
    }

}
