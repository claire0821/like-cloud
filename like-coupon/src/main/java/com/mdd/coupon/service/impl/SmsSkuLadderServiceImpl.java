package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsSkuLadderService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsSkuLadderParam;
import com.mdd.coupon.vo.SmsSkuLadderListVo;
import com.mdd.coupon.vo.SmsSkuLadderDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSkuLadder;
import com.mdd.coupon.mapper.SmsSkuLadderMapper;
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
 * 商品阶梯价格实现类
 */
@Service
public class SmsSkuLadderServiceImpl implements ISmsSkuLadderService {
        
    @Resource
    SmsSkuLadderMapper smsSkuLadderMapper;

    /**
     * 商品阶梯价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSkuLadderListVo>
     */
    @Override
    public PageResult<SmsSkuLadderListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSkuLadder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSkuLadderMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:fullCount@full_count:long",
            "=:discount:str",
            "=:price:str",
            "=:addOther@add_other:int",
        });

        IPage<SmsSkuLadder> iPage = smsSkuLadderMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSkuLadderListVo> list = new LinkedList<>();
        for(SmsSkuLadder item : iPage.getRecords()) {
            SmsSkuLadderListVo vo = new SmsSkuLadderListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品阶梯价格详情
     *
     * @param id 主键参数
     * @return SmsSkuLadder
     */
    @Override
    public SmsSkuLadderDetailVo detail(Long id) {
        SmsSkuLadder model = smsSkuLadderMapper.selectOne(
                new QueryWrapper<SmsSkuLadder>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSkuLadderDetailVo vo = new SmsSkuLadderDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品阶梯价格新增
     *
     * @param smsSkuLadderParam 参数
     */
    @Override
    public void add(SmsSkuLadderParam smsSkuLadderParam) {
        SmsSkuLadder model = new SmsSkuLadder();
        model.setSkuId(smsSkuLadderParam.getSkuId());
        model.setFullCount(smsSkuLadderParam.getFullCount());
        model.setDiscount(smsSkuLadderParam.getDiscount());
        model.setPrice(smsSkuLadderParam.getPrice());
        model.setAddOther(smsSkuLadderParam.getAddOther());
        smsSkuLadderMapper.insert(model);
    }

    /**
     * 商品阶梯价格编辑
     *
     * @param smsSkuLadderParam 参数
     */
    @Override
    public void edit(SmsSkuLadderParam smsSkuLadderParam) {
        SmsSkuLadder model = smsSkuLadderMapper.selectOne(
                new QueryWrapper<SmsSkuLadder>()
                    .eq("id",  smsSkuLadderParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSkuLadderParam.getId());
        model.setSkuId(smsSkuLadderParam.getSkuId());
        model.setFullCount(smsSkuLadderParam.getFullCount());
        model.setDiscount(smsSkuLadderParam.getDiscount());
        model.setPrice(smsSkuLadderParam.getPrice());
        model.setAddOther(smsSkuLadderParam.getAddOther());
        smsSkuLadderMapper.updateById(model);
    }

    /**
     * 商品阶梯价格删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSkuLadder model = smsSkuLadderMapper.selectOne(
                new QueryWrapper<SmsSkuLadder>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSkuLadderMapper.delete(new QueryWrapper<SmsSkuLadder>().eq("id", id));
    }

}
