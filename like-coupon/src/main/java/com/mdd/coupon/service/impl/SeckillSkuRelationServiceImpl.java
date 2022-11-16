package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISeckillSkuRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SeckillSkuRelationParam;
import com.mdd.coupon.vo.SeckillSkuRelationListVo;
import com.mdd.coupon.vo.SeckillSkuRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SeckillSkuRelation;
import com.mdd.coupon.mapper.SeckillSkuRelationMapper;
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
 * 秒杀活动商品关联实现类
 */
@Service
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationMapper,SeckillSkuRelation> implements ISeckillSkuRelationService {
        
    @Resource
    SeckillSkuRelationMapper seckillSkuRelationMapper;

    /**
     * 秒杀活动商品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillSkuRelationListVo>
     */
    @Override
    public PageResult<SeckillSkuRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SeckillSkuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        seckillSkuRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:promotionId@promotion_id:long",
            "=:promotionSessionId@promotion_session_id:long",
            "=:skuId@sku_id:long",
            "=:seckillPrice@seckill_price:str",
            "=:seckillCount@seckill_count:str",
            "=:seckillLimit@seckill_limit:str",
            "=:seckillSort@seckill_sort:int",
        });

        IPage<SeckillSkuRelation> iPage = seckillSkuRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SeckillSkuRelationListVo> list = new LinkedList<>();
        for(SeckillSkuRelation item : iPage.getRecords()) {
            SeckillSkuRelationListVo vo = new SeckillSkuRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀活动商品关联详情
     *
     * @param id 主键参数
     * @return SeckillSkuRelation
     */
    @Override
    public SeckillSkuRelationDetailVo detail(Long id) {
        SeckillSkuRelation model = seckillSkuRelationMapper.selectOne(
                new QueryWrapper<SeckillSkuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SeckillSkuRelationDetailVo vo = new SeckillSkuRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 秒杀活动商品关联新增
     *
     * @param seckillSkuRelationParam 参数
     */
    @Override
    public void add(SeckillSkuRelationParam seckillSkuRelationParam) {
        SeckillSkuRelation model = new SeckillSkuRelation();
        model.setPromotionId(seckillSkuRelationParam.getPromotionId());
        model.setPromotionSessionId(seckillSkuRelationParam.getPromotionSessionId());
        model.setSkuId(seckillSkuRelationParam.getSkuId());
        model.setSeckillPrice(seckillSkuRelationParam.getSeckillPrice());
        model.setSeckillCount(seckillSkuRelationParam.getSeckillCount());
        model.setSeckillLimit(seckillSkuRelationParam.getSeckillLimit());
        model.setSeckillSort(seckillSkuRelationParam.getSeckillSort());
        seckillSkuRelationMapper.insert(model);
    }

    /**
     * 秒杀活动商品关联编辑
     *
     * @param seckillSkuRelationParam 参数
     */
    @Override
    public void edit(SeckillSkuRelationParam seckillSkuRelationParam) {
        SeckillSkuRelation model = seckillSkuRelationMapper.selectOne(
                new QueryWrapper<SeckillSkuRelation>()
                    .eq("id",  seckillSkuRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(seckillSkuRelationParam.getId());
        model.setPromotionId(seckillSkuRelationParam.getPromotionId());
        model.setPromotionSessionId(seckillSkuRelationParam.getPromotionSessionId());
        model.setSkuId(seckillSkuRelationParam.getSkuId());
        model.setSeckillPrice(seckillSkuRelationParam.getSeckillPrice());
        model.setSeckillCount(seckillSkuRelationParam.getSeckillCount());
        model.setSeckillLimit(seckillSkuRelationParam.getSeckillLimit());
        model.setSeckillSort(seckillSkuRelationParam.getSeckillSort());
        seckillSkuRelationMapper.updateById(model);
    }

    /**
     * 秒杀活动商品关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SeckillSkuRelation model = seckillSkuRelationMapper.selectOne(
                new QueryWrapper<SeckillSkuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        seckillSkuRelationMapper.delete(new QueryWrapper<SeckillSkuRelation>().eq("id", id));
    }

}
