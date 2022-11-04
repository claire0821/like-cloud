package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsSeckillSkuRelationService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsSeckillSkuRelationParam;
import com.mdd.coupon.vo.SmsSeckillSkuRelationListVo;
import com.mdd.coupon.vo.SmsSeckillSkuRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSeckillSkuRelation;
import com.mdd.coupon.mapper.SmsSeckillSkuRelationMapper;
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
public class SmsSeckillSkuRelationServiceImpl implements ISmsSeckillSkuRelationService {
        
    @Resource
    SmsSeckillSkuRelationMapper smsSeckillSkuRelationMapper;

    /**
     * 秒杀活动商品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillSkuRelationListVo>
     */
    @Override
    public PageResult<SmsSeckillSkuRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSeckillSkuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSeckillSkuRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:promotionId@promotion_id:long",
            "=:promotionSessionId@promotion_session_id:long",
            "=:skuId@sku_id:long",
            "=:seckillPrice@seckill_price:long",
            "=:seckillCount@seckill_count:long",
            "=:seckillLimit@seckill_limit:long",
            "=:seckillSort@seckill_sort:long",
        });

        IPage<SmsSeckillSkuRelation> iPage = smsSeckillSkuRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSeckillSkuRelationListVo> list = new LinkedList<>();
        for(SmsSeckillSkuRelation item : iPage.getRecords()) {
            SmsSeckillSkuRelationListVo vo = new SmsSeckillSkuRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀活动商品关联详情
     *
     * @param id 主键参数
     * @return SmsSeckillSkuRelation
     */
    @Override
    public SmsSeckillSkuRelationDetailVo detail(Long id) {
        SmsSeckillSkuRelation model = smsSeckillSkuRelationMapper.selectOne(
                new QueryWrapper<SmsSeckillSkuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSeckillSkuRelationDetailVo vo = new SmsSeckillSkuRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 秒杀活动商品关联新增
     *
     * @param smsSeckillSkuRelationParam 参数
     */
    @Override
    public void add(SmsSeckillSkuRelationParam smsSeckillSkuRelationParam) {
        SmsSeckillSkuRelation model = new SmsSeckillSkuRelation();
        model.setPromotionId(smsSeckillSkuRelationParam.getPromotionId());
        model.setPromotionSessionId(smsSeckillSkuRelationParam.getPromotionSessionId());
        model.setSkuId(smsSeckillSkuRelationParam.getSkuId());
        model.setSeckillPrice(smsSeckillSkuRelationParam.getSeckillPrice());
        model.setSeckillCount(smsSeckillSkuRelationParam.getSeckillCount());
        model.setSeckillLimit(smsSeckillSkuRelationParam.getSeckillLimit());
        model.setSeckillSort(smsSeckillSkuRelationParam.getSeckillSort());
        smsSeckillSkuRelationMapper.insert(model);
    }

    /**
     * 秒杀活动商品关联编辑
     *
     * @param smsSeckillSkuRelationParam 参数
     */
    @Override
    public void edit(SmsSeckillSkuRelationParam smsSeckillSkuRelationParam) {
        SmsSeckillSkuRelation model = smsSeckillSkuRelationMapper.selectOne(
                new QueryWrapper<SmsSeckillSkuRelation>()
                    .eq("id",  smsSeckillSkuRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSeckillSkuRelationParam.getId());
        model.setPromotionId(smsSeckillSkuRelationParam.getPromotionId());
        model.setPromotionSessionId(smsSeckillSkuRelationParam.getPromotionSessionId());
        model.setSkuId(smsSeckillSkuRelationParam.getSkuId());
        model.setSeckillPrice(smsSeckillSkuRelationParam.getSeckillPrice());
        model.setSeckillCount(smsSeckillSkuRelationParam.getSeckillCount());
        model.setSeckillLimit(smsSeckillSkuRelationParam.getSeckillLimit());
        model.setSeckillSort(smsSeckillSkuRelationParam.getSeckillSort());
        smsSeckillSkuRelationMapper.updateById(model);
    }

    /**
     * 秒杀活动商品关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSeckillSkuRelation model = smsSeckillSkuRelationMapper.selectOne(
                new QueryWrapper<SmsSeckillSkuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSeckillSkuRelationMapper.delete(new QueryWrapper<SmsSeckillSkuRelation>().eq("id", id));
    }

}
