package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsSeckillSkuNoticeService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillSkuNoticeParam;
import com.mdd.coupon.vo.SmsSeckillSkuNoticeListVo;
import com.mdd.coupon.vo.SmsSeckillSkuNoticeDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSeckillSkuNotice;
import com.mdd.coupon.mapper.SmsSeckillSkuNoticeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 秒杀商品通知订阅实现类
 */
@Service
public class SmsSeckillSkuNoticeServiceImpl implements ISmsSeckillSkuNoticeService {
        
    @Resource
    SmsSeckillSkuNoticeMapper smsSeckillSkuNoticeMapper;

    /**
     * 秒杀商品通知订阅列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillSkuNoticeListVo>
     */
    @Override
    public PageResult<SmsSeckillSkuNoticeListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSeckillSkuNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSeckillSkuNoticeMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:skuId@sku_id:long",
            "=:sessionId@session_id:long",
            "=:subcribeTime@subcribe_time:str",
            "=:sendTime@send_time:str",
            "=:noticeType@notice_type:int",
        });

        IPage<SmsSeckillSkuNotice> iPage = smsSeckillSkuNoticeMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSeckillSkuNoticeListVo> list = new LinkedList<>();
        for(SmsSeckillSkuNotice item : iPage.getRecords()) {
            SmsSeckillSkuNoticeListVo vo = new SmsSeckillSkuNoticeListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀商品通知订阅详情
     *
     * @param id 主键参数
     * @return SmsSeckillSkuNotice
     */
    @Override
    public SmsSeckillSkuNoticeDetailVo detail(Long id) {
        SmsSeckillSkuNotice model = smsSeckillSkuNoticeMapper.selectOne(
                new QueryWrapper<SmsSeckillSkuNotice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSeckillSkuNoticeDetailVo vo = new SmsSeckillSkuNoticeDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 秒杀商品通知订阅新增
     *
     * @param smsSeckillSkuNoticeParam 参数
     */
    @Override
    public void add(SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam) {
        SmsSeckillSkuNotice model = new SmsSeckillSkuNotice();
        model.setMemberId(smsSeckillSkuNoticeParam.getMemberId());
        model.setSkuId(smsSeckillSkuNoticeParam.getSkuId());
        model.setSessionId(smsSeckillSkuNoticeParam.getSessionId());
        model.setSubcribeTime(smsSeckillSkuNoticeParam.getSubcribeTime());
        model.setSendTime(smsSeckillSkuNoticeParam.getSendTime());
        model.setNoticeType(smsSeckillSkuNoticeParam.getNoticeType());
        smsSeckillSkuNoticeMapper.insert(model);
    }

    /**
     * 秒杀商品通知订阅编辑
     *
     * @param smsSeckillSkuNoticeParam 参数
     */
    @Override
    public void edit(SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam) {
        SmsSeckillSkuNotice model = smsSeckillSkuNoticeMapper.selectOne(
                new QueryWrapper<SmsSeckillSkuNotice>()
                    .eq("id",  smsSeckillSkuNoticeParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSeckillSkuNoticeParam.getId());
        model.setMemberId(smsSeckillSkuNoticeParam.getMemberId());
        model.setSkuId(smsSeckillSkuNoticeParam.getSkuId());
        model.setSessionId(smsSeckillSkuNoticeParam.getSessionId());
        model.setSubcribeTime(smsSeckillSkuNoticeParam.getSubcribeTime());
        model.setSendTime(smsSeckillSkuNoticeParam.getSendTime());
        model.setNoticeType(smsSeckillSkuNoticeParam.getNoticeType());
        smsSeckillSkuNoticeMapper.updateById(model);
    }

    /**
     * 秒杀商品通知订阅删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSeckillSkuNotice model = smsSeckillSkuNoticeMapper.selectOne(
                new QueryWrapper<SmsSeckillSkuNotice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSeckillSkuNoticeMapper.delete(new QueryWrapper<SmsSeckillSkuNotice>().eq("id", id));
    }

}
