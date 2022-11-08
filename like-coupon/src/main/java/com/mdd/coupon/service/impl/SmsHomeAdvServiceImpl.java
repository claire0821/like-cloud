package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsHomeAdvService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsHomeAdvParam;
import com.mdd.coupon.vo.SmsHomeAdvListVo;
import com.mdd.coupon.vo.SmsHomeAdvDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsHomeAdv;
import com.mdd.coupon.mapper.SmsHomeAdvMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 首页轮播广告实现类
 */
@Service
public class SmsHomeAdvServiceImpl implements ISmsHomeAdvService {
        
    @Resource
    SmsHomeAdvMapper smsHomeAdvMapper;

    /**
     * 首页轮播广告列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsHomeAdvListVo>
     */
    @Override
    public PageResult<SmsHomeAdvListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsHomeAdv> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        smsHomeAdvMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:pic:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:status:int",
            "=:clickCount@click_count:long",
            "=:url:str",
            "=:note:str",
            "=:sort:long",
            "=:publisherId@publisher_id:long",
            "=:authId@auth_id:long",
        });

        IPage<SmsHomeAdv> iPage = smsHomeAdvMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsHomeAdvListVo> list = new LinkedList<>();
        for(SmsHomeAdv item : iPage.getRecords()) {
            SmsHomeAdvListVo vo = new SmsHomeAdvListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setStartTime(TimeUtil.timestampToDate(item.getStartTime()));
//            vo.setEndTime(TimeUtil.timestampToDate(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 首页轮播广告详情
     *
     * @param id 主键参数
     * @return SmsHomeAdv
     */
    @Override
    public SmsHomeAdvDetailVo detail(Long id) {
        SmsHomeAdv model = smsHomeAdvMapper.selectOne(
                new QueryWrapper<SmsHomeAdv>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsHomeAdvDetailVo vo = new SmsHomeAdvDetailVo();
        BeanUtils.copyProperties(model, vo);
//        vo.setStartTime(TimeUtil.timestampToDate(model.getStartTime()));
//        vo.setEndTime(TimeUtil.timestampToDate(model.getEndTime()));
        return vo;
    }

    /**
     * 首页轮播广告新增
     *
     * @param smsHomeAdvParam 参数
     */
    @Override
    public void add(SmsHomeAdvParam smsHomeAdvParam) {
        SmsHomeAdv model = new SmsHomeAdv();
        model.setName(smsHomeAdvParam.getName());
        model.setPic(smsHomeAdvParam.getPic());
//        model.setStartTime(System.currentTimeMillis() / 1000);
//        model.setEndTime(System.currentTimeMillis() / 1000);
        model.setStatus(smsHomeAdvParam.getStatus());
        model.setClickCount(smsHomeAdvParam.getClickCount());
        model.setUrl(smsHomeAdvParam.getUrl());
        model.setNote(smsHomeAdvParam.getNote());
        model.setSort(smsHomeAdvParam.getSort());
        model.setPublisherId(smsHomeAdvParam.getPublisherId());
        model.setAuthId(smsHomeAdvParam.getAuthId());
        smsHomeAdvMapper.insert(model);
    }

    /**
     * 首页轮播广告编辑
     *
     * @param smsHomeAdvParam 参数
     */
    @Override
    public void edit(SmsHomeAdvParam smsHomeAdvParam) {
        SmsHomeAdv model = smsHomeAdvMapper.selectOne(
                new QueryWrapper<SmsHomeAdv>()
                    .eq("id",  smsHomeAdvParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsHomeAdvParam.getId());
        model.setName(smsHomeAdvParam.getName());
        model.setPic(smsHomeAdvParam.getPic());
        model.setStatus(smsHomeAdvParam.getStatus());
        model.setClickCount(smsHomeAdvParam.getClickCount());
        model.setUrl(smsHomeAdvParam.getUrl());
        model.setNote(smsHomeAdvParam.getNote());
        model.setSort(smsHomeAdvParam.getSort());
        model.setPublisherId(smsHomeAdvParam.getPublisherId());
        model.setAuthId(smsHomeAdvParam.getAuthId());
        smsHomeAdvMapper.updateById(model);
    }

    /**
     * 首页轮播广告删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsHomeAdv model = smsHomeAdvMapper.selectOne(
                new QueryWrapper<SmsHomeAdv>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsHomeAdvMapper.delete(new QueryWrapper<SmsHomeAdv>().eq("id", id));
    }

}
