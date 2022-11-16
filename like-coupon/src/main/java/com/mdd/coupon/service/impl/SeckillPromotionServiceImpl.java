package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISeckillPromotionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SeckillPromotionParam;
import com.mdd.coupon.vo.SeckillPromotionListVo;
import com.mdd.coupon.vo.SeckillPromotionDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SeckillPromotion;
import com.mdd.coupon.mapper.SeckillPromotionMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 秒杀活动实现类
 */
@Service
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionMapper,SeckillPromotion> implements ISeckillPromotionService {
        
    @Resource
    SeckillPromotionMapper seckillPromotionMapper;

    /**
     * 秒杀活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillPromotionListVo>
     */
    @Override
    public PageResult<SeckillPromotionListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SeckillPromotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        seckillPromotionMapper.setSearch(queryWrapper, params, new String[]{
            "like:title:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:status:int",
            "=:userId@user_id:long",
        });

        IPage<SeckillPromotion> iPage = seckillPromotionMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SeckillPromotionListVo> list = new LinkedList<>();
        for(SeckillPromotion item : iPage.getRecords()) {
            SeckillPromotionListVo vo = new SeckillPromotionListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            vo.setStartTime(simpleDateFormat.format(item.getStartTime()));
            vo.setEndTime(simpleDateFormat.format(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀活动详情
     *
     * @param id 主键参数
     * @return SeckillPromotion
     */
    @Override
    public SeckillPromotionDetailVo detail(Long id) {
        SeckillPromotion model = seckillPromotionMapper.selectOne(
                new QueryWrapper<SeckillPromotion>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SeckillPromotionDetailVo vo = new SeckillPromotionDetailVo();
        BeanUtils.copyProperties(model, vo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setStartTime(simpleDateFormat.format(model.getStartTime()));
        vo.setEndTime(simpleDateFormat.format(model.getEndTime()));
        return vo;
    }

    /**
     * 秒杀活动新增
     *
     * @param seckillPromotionParam 参数
     */
    @Override
    public void add(SeckillPromotionParam seckillPromotionParam) {
        SeckillPromotion model = new SeckillPromotion();
        model.setTitle(seckillPromotionParam.getTitle());
        model.setStartTime(new Date());
        model.setEndTime(new Date());
        model.setStatus(seckillPromotionParam.getStatus());
        model.setCreateTime(new Date());
        model.setUserId(seckillPromotionParam.getUserId());
        seckillPromotionMapper.insert(model);
    }

    /**
     * 秒杀活动编辑
     *
     * @param seckillPromotionParam 参数
     */
    @Override
    public void edit(SeckillPromotionParam seckillPromotionParam) {
        SeckillPromotion model = seckillPromotionMapper.selectOne(
                new QueryWrapper<SeckillPromotion>()
                    .eq("id",  seckillPromotionParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(seckillPromotionParam.getId());
        model.setTitle(seckillPromotionParam.getTitle());
        model.setStatus(seckillPromotionParam.getStatus());
        model.setUserId(seckillPromotionParam.getUserId());
        seckillPromotionMapper.updateById(model);
    }

    /**
     * 秒杀活动删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SeckillPromotion model = seckillPromotionMapper.selectOne(
                new QueryWrapper<SeckillPromotion>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        seckillPromotionMapper.delete(new QueryWrapper<SeckillPromotion>().eq("id", id));
    }

}
