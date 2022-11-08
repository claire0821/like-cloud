package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsSpuBoundsService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSpuBoundsParam;
import com.mdd.coupon.vo.SmsSpuBoundsListVo;
import com.mdd.coupon.vo.SmsSpuBoundsDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSpuBounds;
import com.mdd.coupon.mapper.SmsSpuBoundsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品spu积分设置实现类
 */
@Service
public class SmsSpuBoundsServiceImpl implements ISmsSpuBoundsService {
        
    @Resource
    SmsSpuBoundsMapper smsSpuBoundsMapper;

    /**
     * 商品spu积分设置列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSpuBoundsListVo>
     */
    @Override
    public PageResult<SmsSpuBoundsListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSpuBounds> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSpuBoundsMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "=:growBounds@grow_bounds:str",
            "=:buyBounds@buy_bounds:str",
            "=:work:int",
        });

        IPage<SmsSpuBounds> iPage = smsSpuBoundsMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSpuBoundsListVo> list = new LinkedList<>();
        for(SmsSpuBounds item : iPage.getRecords()) {
            SmsSpuBoundsListVo vo = new SmsSpuBoundsListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品spu积分设置详情
     *
     * @param id 主键参数
     * @return SmsSpuBounds
     */
    @Override
    public SmsSpuBoundsDetailVo detail(Long id) {
        SmsSpuBounds model = smsSpuBoundsMapper.selectOne(
                new QueryWrapper<SmsSpuBounds>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSpuBoundsDetailVo vo = new SmsSpuBoundsDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品spu积分设置新增
     *
     * @param smsSpuBoundsParam 参数
     */
    @Override
    public void add(SmsSpuBoundsParam smsSpuBoundsParam) {
        SmsSpuBounds model = new SmsSpuBounds();
        model.setSpuId(smsSpuBoundsParam.getSpuId());
        model.setGrowBounds(smsSpuBoundsParam.getGrowBounds());
        model.setBuyBounds(smsSpuBoundsParam.getBuyBounds());
        model.setWork(smsSpuBoundsParam.getWork());
        smsSpuBoundsMapper.insert(model);
    }

    /**
     * 商品spu积分设置编辑
     *
     * @param smsSpuBoundsParam 参数
     */
    @Override
    public void edit(SmsSpuBoundsParam smsSpuBoundsParam) {
        SmsSpuBounds model = smsSpuBoundsMapper.selectOne(
                new QueryWrapper<SmsSpuBounds>()
                    .eq("id",  smsSpuBoundsParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSpuBoundsParam.getId());
        model.setSpuId(smsSpuBoundsParam.getSpuId());
        model.setGrowBounds(smsSpuBoundsParam.getGrowBounds());
        model.setBuyBounds(smsSpuBoundsParam.getBuyBounds());
        model.setWork(smsSpuBoundsParam.getWork());
        smsSpuBoundsMapper.updateById(model);
    }

    /**
     * 商品spu积分设置删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSpuBounds model = smsSpuBoundsMapper.selectOne(
                new QueryWrapper<SmsSpuBounds>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSpuBoundsMapper.delete(new QueryWrapper<SmsSpuBounds>().eq("id", id));
    }

}
