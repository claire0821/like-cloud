package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsCouponService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsCouponParam;
import com.mdd.coupon.vo.SmsCouponListVo;
import com.mdd.coupon.vo.SmsCouponDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsCoupon;
import com.mdd.coupon.mapper.SmsCouponMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 优惠券信息实现类
 */
@Service
public class SmsCouponServiceImpl implements ISmsCouponService {
        
    @Resource
    SmsCouponMapper smsCouponMapper;

    /**
     * 优惠券信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponListVo>
     */
    @Override
    public PageResult<SmsCouponListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsCouponMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponType@coupon_type:int",
            "=:couponImg@coupon_img:str",
            "like:couponName@coupon_name:str",
            "=:num:long",
            "=:amount:str",
            "=:perLimit@per_limit:long",
            "=:minPoint@min_point:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:useType@use_type:int",
            "=:note:str",
            "=:publishCount@publish_count:long",
            "=:useCount@use_count:long",
            "=:receiveCount@receive_count:long",
            "=:enableStartTime@enable_start_time:str",
            "=:enableEndTime@enable_end_time:str",
            "=:code:str",
            "=:memberLevel@member_level:int",
            "=:publish:int",
        });

        IPage<SmsCoupon> iPage = smsCouponMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsCouponListVo> list = new LinkedList<>();
        for(SmsCoupon item : iPage.getRecords()) {
            SmsCouponListVo vo = new SmsCouponListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setStartTime(TimeUtil.timestampToDate(item.getStartTime()));
//            vo.setEndTime(TimeUtil.timestampToDate(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券信息详情
     *
     * @param id 主键参数
     * @return SmsCoupon
     */
    @Override
    public SmsCouponDetailVo detail(Long id) {
        SmsCoupon model = smsCouponMapper.selectOne(
                new QueryWrapper<SmsCoupon>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsCouponDetailVo vo = new SmsCouponDetailVo();
        BeanUtils.copyProperties(model, vo);
//        vo.setStartTime(TimeUtil.timestampToDate(model.getStartTime()));
//        vo.setEndTime(TimeUtil.timestampToDate(model.getEndTime()));
        return vo;
    }

    /**
     * 优惠券信息新增
     *
     * @param smsCouponParam 参数
     */
    @Override
    public void add(SmsCouponParam smsCouponParam) {
        SmsCoupon model = new SmsCoupon();
        model.setCouponType(smsCouponParam.getCouponType());
        model.setCouponImg(smsCouponParam.getCouponImg());
        model.setCouponName(smsCouponParam.getCouponName());
        model.setNum(smsCouponParam.getNum());
        model.setAmount(smsCouponParam.getAmount());
        model.setPerLimit(smsCouponParam.getPerLimit());
        model.setMinPoint(smsCouponParam.getMinPoint());
//        model.setStartTime(System.currentTimeMillis() / 1000);
//        model.setEndTime(System.currentTimeMillis() / 1000);
        model.setUseType(smsCouponParam.getUseType());
        model.setNote(smsCouponParam.getNote());
        model.setPublishCount(smsCouponParam.getPublishCount());
        model.setUseCount(smsCouponParam.getUseCount());
        model.setReceiveCount(smsCouponParam.getReceiveCount());
        model.setEnableStartTime(smsCouponParam.getEnableStartTime());
        model.setEnableEndTime(smsCouponParam.getEnableEndTime());
        model.setCode(smsCouponParam.getCode());
        model.setMemberLevel(smsCouponParam.getMemberLevel());
        model.setPublish(smsCouponParam.getPublish());
        smsCouponMapper.insert(model);
    }

    /**
     * 优惠券信息编辑
     *
     * @param smsCouponParam 参数
     */
    @Override
    public void edit(SmsCouponParam smsCouponParam) {
        SmsCoupon model = smsCouponMapper.selectOne(
                new QueryWrapper<SmsCoupon>()
                    .eq("id",  smsCouponParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsCouponParam.getId());
        model.setCouponType(smsCouponParam.getCouponType());
        model.setCouponImg(smsCouponParam.getCouponImg());
        model.setCouponName(smsCouponParam.getCouponName());
        model.setNum(smsCouponParam.getNum());
        model.setAmount(smsCouponParam.getAmount());
        model.setPerLimit(smsCouponParam.getPerLimit());
        model.setMinPoint(smsCouponParam.getMinPoint());
        model.setUseType(smsCouponParam.getUseType());
        model.setNote(smsCouponParam.getNote());
        model.setPublishCount(smsCouponParam.getPublishCount());
        model.setUseCount(smsCouponParam.getUseCount());
        model.setReceiveCount(smsCouponParam.getReceiveCount());
        model.setEnableStartTime(smsCouponParam.getEnableStartTime());
        model.setEnableEndTime(smsCouponParam.getEnableEndTime());
        model.setCode(smsCouponParam.getCode());
        model.setMemberLevel(smsCouponParam.getMemberLevel());
        model.setPublish(smsCouponParam.getPublish());
        smsCouponMapper.updateById(model);
    }

    /**
     * 优惠券信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsCoupon model = smsCouponMapper.selectOne(
                new QueryWrapper<SmsCoupon>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsCouponMapper.delete(new QueryWrapper<SmsCoupon>().eq("id", id));
    }

}
