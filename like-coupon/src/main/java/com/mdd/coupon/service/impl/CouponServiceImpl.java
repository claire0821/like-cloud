package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ICouponService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.CouponParam;
import com.mdd.coupon.vo.CouponListVo;
import com.mdd.coupon.vo.CouponDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.Coupon;
import com.mdd.coupon.mapper.CouponMapper;
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
 * 优惠券信息实现类
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper,Coupon> implements ICouponService {
        
    @Resource
    CouponMapper couponMapper;

    /**
     * 优惠券信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponListVo>
     */
    @Override
    public PageResult<CouponListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        couponMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponType@coupon_type:int",
            "=:couponImg@coupon_img:str",
            "like:couponName@coupon_name:str",
            "=:num:int",
            "=:amount:str",
            "=:perLimit@per_limit:int",
            "=:minPoint@min_point:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:useType@use_type:int",
            "=:note:str",
            "=:publishCount@publish_count:int",
            "=:useCount@use_count:int",
            "=:receiveCount@receive_count:int",
            "=:enableStartTime@enable_start_time:str",
            "=:enableEndTime@enable_end_time:str",
            "=:code:str",
            "=:memberLevel@member_level:int",
            "=:publish:int",
        });

        IPage<Coupon> iPage = couponMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CouponListVo> list = new LinkedList<>();
        for(Coupon item : iPage.getRecords()) {
            CouponListVo vo = new CouponListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setStartTime(simpleDateFormat.format(item.getStartTime()));
            vo.setEndTime(simpleDateFormat.format(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券信息详情
     *
     * @param id 主键参数
     * @return Coupon
     */
    @Override
    public CouponDetailVo detail(Long id) {
        Coupon model = couponMapper.selectOne(
                new QueryWrapper<Coupon>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CouponDetailVo vo = new CouponDetailVo();
        BeanUtils.copyProperties(model, vo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setStartTime(simpleDateFormat.format(model.getStartTime()));
        vo.setEndTime(simpleDateFormat.format(model.getEndTime()));
        return vo;
    }

    /**
     * 优惠券信息新增
     *
     * @param couponParam 参数
     */
    @Override
    public void add(CouponParam couponParam) {
        Coupon model = new Coupon();
        model.setCouponType(couponParam.getCouponType());
        model.setCouponImg(couponParam.getCouponImg());
        model.setCouponName(couponParam.getCouponName());
        model.setNum(couponParam.getNum());
        model.setAmount(couponParam.getAmount());
        model.setPerLimit(couponParam.getPerLimit());
        model.setMinPoint(couponParam.getMinPoint());
        model.setStartTime(new Date());
        model.setEndTime(new Date());
        model.setUseType(couponParam.getUseType());
        model.setNote(couponParam.getNote());
        model.setPublishCount(couponParam.getPublishCount());
        model.setUseCount(couponParam.getUseCount());
        model.setReceiveCount(couponParam.getReceiveCount());
        model.setEnableStartTime(couponParam.getEnableStartTime());
        model.setEnableEndTime(couponParam.getEnableEndTime());
        model.setCode(couponParam.getCode());
        model.setMemberLevel(couponParam.getMemberLevel());
        model.setPublish(couponParam.getPublish());
        couponMapper.insert(model);
    }

    /**
     * 优惠券信息编辑
     *
     * @param couponParam 参数
     */
    @Override
    public void edit(CouponParam couponParam) {
        Coupon model = couponMapper.selectOne(
                new QueryWrapper<Coupon>()
                    .eq("id",  couponParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(couponParam.getId());
        model.setCouponType(couponParam.getCouponType());
        model.setCouponImg(couponParam.getCouponImg());
        model.setCouponName(couponParam.getCouponName());
        model.setNum(couponParam.getNum());
        model.setAmount(couponParam.getAmount());
        model.setPerLimit(couponParam.getPerLimit());
        model.setMinPoint(couponParam.getMinPoint());
        model.setUseType(couponParam.getUseType());
        model.setNote(couponParam.getNote());
        model.setPublishCount(couponParam.getPublishCount());
        model.setUseCount(couponParam.getUseCount());
        model.setReceiveCount(couponParam.getReceiveCount());
        model.setEnableStartTime(couponParam.getEnableStartTime());
        model.setEnableEndTime(couponParam.getEnableEndTime());
        model.setCode(couponParam.getCode());
        model.setMemberLevel(couponParam.getMemberLevel());
        model.setPublish(couponParam.getPublish());
        couponMapper.updateById(model);
    }

    /**
     * 优惠券信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Coupon model = couponMapper.selectOne(
                new QueryWrapper<Coupon>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        couponMapper.delete(new QueryWrapper<Coupon>().eq("id", id));
    }

}
