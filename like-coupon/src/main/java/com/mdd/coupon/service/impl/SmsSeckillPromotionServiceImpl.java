package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsSeckillPromotionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillPromotionParam;
import com.mdd.coupon.vo.SmsSeckillPromotionListVo;
import com.mdd.coupon.vo.SmsSeckillPromotionDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSeckillPromotion;
import com.mdd.coupon.mapper.SmsSeckillPromotionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 秒杀活动实现类
 */
@Service
public class SmsSeckillPromotionServiceImpl implements ISmsSeckillPromotionService {
        
    @Resource
    SmsSeckillPromotionMapper smsSeckillPromotionMapper;

    /**
     * 秒杀活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillPromotionListVo>
     */
    @Override
    public PageResult<SmsSeckillPromotionListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSeckillPromotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSeckillPromotionMapper.setSearch(queryWrapper, params, new String[]{
            "like:title:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:status:long",
            "=:userId@user_id:long",
        });

        IPage<SmsSeckillPromotion> iPage = smsSeckillPromotionMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSeckillPromotionListVo> list = new LinkedList<>();
        for(SmsSeckillPromotion item : iPage.getRecords()) {
            SmsSeckillPromotionListVo vo = new SmsSeckillPromotionListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
//            vo.setStartTime(TimeUtil.timestampToDate(item.getStartTime()));
//            vo.setEndTime(TimeUtil.timestampToDate(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀活动详情
     *
     * @param id 主键参数
     * @return SmsSeckillPromotion
     */
    @Override
    public SmsSeckillPromotionDetailVo detail(Long id) {
        SmsSeckillPromotion model = smsSeckillPromotionMapper.selectOne(
                new QueryWrapper<SmsSeckillPromotion>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSeckillPromotionDetailVo vo = new SmsSeckillPromotionDetailVo();
        BeanUtils.copyProperties(model, vo);
//        vo.setStartTime(TimeUtil.timestampToDate(model.getStartTime()));
//        vo.setEndTime(TimeUtil.timestampToDate(model.getEndTime()));
        return vo;
    }

    /**
     * 秒杀活动新增
     *
     * @param smsSeckillPromotionParam 参数
     */
    @Override
    public void add(SmsSeckillPromotionParam smsSeckillPromotionParam) {
        SmsSeckillPromotion model = new SmsSeckillPromotion();
        model.setTitle(smsSeckillPromotionParam.getTitle());
//        model.setStartTime(System.currentTimeMillis() / 1000);
//        model.setEndTime(System.currentTimeMillis() / 1000);
        model.setStatus(smsSeckillPromotionParam.getStatus());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUserId(smsSeckillPromotionParam.getUserId());
        smsSeckillPromotionMapper.insert(model);
    }

    /**
     * 秒杀活动编辑
     *
     * @param smsSeckillPromotionParam 参数
     */
    @Override
    public void edit(SmsSeckillPromotionParam smsSeckillPromotionParam) {
        SmsSeckillPromotion model = smsSeckillPromotionMapper.selectOne(
                new QueryWrapper<SmsSeckillPromotion>()
                    .eq("id",  smsSeckillPromotionParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSeckillPromotionParam.getId());
        model.setTitle(smsSeckillPromotionParam.getTitle());
        model.setStatus(smsSeckillPromotionParam.getStatus());
        model.setUserId(smsSeckillPromotionParam.getUserId());
        smsSeckillPromotionMapper.updateById(model);
    }

    /**
     * 秒杀活动删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSeckillPromotion model = smsSeckillPromotionMapper.selectOne(
                new QueryWrapper<SmsSeckillPromotion>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSeckillPromotionMapper.delete(new QueryWrapper<SmsSeckillPromotion>().eq("id", id));
    }

}
