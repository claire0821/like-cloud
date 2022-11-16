package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISeckillSkuNoticeService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SeckillSkuNoticeParam;
import com.mdd.coupon.vo.SeckillSkuNoticeListVo;
import com.mdd.coupon.vo.SeckillSkuNoticeDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SeckillSkuNotice;
import com.mdd.coupon.mapper.SeckillSkuNoticeMapper;
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
 * 秒杀商品通知订阅实现类
 */
@Service
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeMapper,SeckillSkuNotice> implements ISeckillSkuNoticeService {
        
    @Resource
    SeckillSkuNoticeMapper seckillSkuNoticeMapper;

    /**
     * 秒杀商品通知订阅列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillSkuNoticeListVo>
     */
    @Override
    public PageResult<SeckillSkuNoticeListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SeckillSkuNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        seckillSkuNoticeMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:skuId@sku_id:long",
            "=:sessionId@session_id:long",
            "=:subcribeTime@subcribe_time:str",
            "=:sendTime@send_time:str",
            "=:noticeType@notice_type:int",
        });

        IPage<SeckillSkuNotice> iPage = seckillSkuNoticeMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SeckillSkuNoticeListVo> list = new LinkedList<>();
        for(SeckillSkuNotice item : iPage.getRecords()) {
            SeckillSkuNoticeListVo vo = new SeckillSkuNoticeListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀商品通知订阅详情
     *
     * @param id 主键参数
     * @return SeckillSkuNotice
     */
    @Override
    public SeckillSkuNoticeDetailVo detail(Long id) {
        SeckillSkuNotice model = seckillSkuNoticeMapper.selectOne(
                new QueryWrapper<SeckillSkuNotice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SeckillSkuNoticeDetailVo vo = new SeckillSkuNoticeDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 秒杀商品通知订阅新增
     *
     * @param seckillSkuNoticeParam 参数
     */
    @Override
    public void add(SeckillSkuNoticeParam seckillSkuNoticeParam) {
        SeckillSkuNotice model = new SeckillSkuNotice();
        model.setMemberId(seckillSkuNoticeParam.getMemberId());
        model.setSkuId(seckillSkuNoticeParam.getSkuId());
        model.setSessionId(seckillSkuNoticeParam.getSessionId());
        model.setSubcribeTime(seckillSkuNoticeParam.getSubcribeTime());
        model.setSendTime(seckillSkuNoticeParam.getSendTime());
        model.setNoticeType(seckillSkuNoticeParam.getNoticeType());
        seckillSkuNoticeMapper.insert(model);
    }

    /**
     * 秒杀商品通知订阅编辑
     *
     * @param seckillSkuNoticeParam 参数
     */
    @Override
    public void edit(SeckillSkuNoticeParam seckillSkuNoticeParam) {
        SeckillSkuNotice model = seckillSkuNoticeMapper.selectOne(
                new QueryWrapper<SeckillSkuNotice>()
                    .eq("id",  seckillSkuNoticeParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(seckillSkuNoticeParam.getId());
        model.setMemberId(seckillSkuNoticeParam.getMemberId());
        model.setSkuId(seckillSkuNoticeParam.getSkuId());
        model.setSessionId(seckillSkuNoticeParam.getSessionId());
        model.setSubcribeTime(seckillSkuNoticeParam.getSubcribeTime());
        model.setSendTime(seckillSkuNoticeParam.getSendTime());
        model.setNoticeType(seckillSkuNoticeParam.getNoticeType());
        seckillSkuNoticeMapper.updateById(model);
    }

    /**
     * 秒杀商品通知订阅删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SeckillSkuNotice model = seckillSkuNoticeMapper.selectOne(
                new QueryWrapper<SeckillSkuNotice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        seckillSkuNoticeMapper.delete(new QueryWrapper<SeckillSkuNotice>().eq("id", id));
    }

}
