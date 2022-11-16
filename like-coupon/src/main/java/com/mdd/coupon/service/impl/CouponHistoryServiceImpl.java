package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ICouponHistoryService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.CouponHistoryParam;
import com.mdd.coupon.vo.CouponHistoryListVo;
import com.mdd.coupon.vo.CouponHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.CouponHistory;
import com.mdd.coupon.mapper.CouponHistoryMapper;
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
 * 优惠券领取历史记录实现类
 */
@Service
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper,CouponHistory> implements ICouponHistoryService {
        
    @Resource
    CouponHistoryMapper couponHistoryMapper;

    /**
     * 优惠券领取历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponHistoryListVo>
     */
    @Override
    public PageResult<CouponHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<CouponHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        couponHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:memberId@member_id:long",
            "like:memberNickName@member_nick_name:str",
            "=:getType@get_type:int",
            "=:useType@use_type:int",
            "=:useTime@use_time:str",
            "=:orderId@order_id:long",
            "=:orderSn@order_sn:long",
        });

        IPage<CouponHistory> iPage = couponHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CouponHistoryListVo> list = new LinkedList<>();
        for(CouponHistory item : iPage.getRecords()) {
            CouponHistoryListVo vo = new CouponHistoryListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券领取历史记录详情
     *
     * @param id 主键参数
     * @return CouponHistory
     */
    @Override
    public CouponHistoryDetailVo detail(Long id) {
        CouponHistory model = couponHistoryMapper.selectOne(
                new QueryWrapper<CouponHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CouponHistoryDetailVo vo = new CouponHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券领取历史记录新增
     *
     * @param couponHistoryParam 参数
     */
    @Override
    public void add(CouponHistoryParam couponHistoryParam) {
        CouponHistory model = new CouponHistory();
        model.setCouponId(couponHistoryParam.getCouponId());
        model.setMemberId(couponHistoryParam.getMemberId());
        model.setMemberNickName(couponHistoryParam.getMemberNickName());
        model.setGetType(couponHistoryParam.getGetType());
        model.setCreateTime(new Date());
        model.setUseType(couponHistoryParam.getUseType());
        model.setUseTime(couponHistoryParam.getUseTime());
        model.setOrderId(couponHistoryParam.getOrderId());
        model.setOrderSn(couponHistoryParam.getOrderSn());
        couponHistoryMapper.insert(model);
    }

    /**
     * 优惠券领取历史记录编辑
     *
     * @param couponHistoryParam 参数
     */
    @Override
    public void edit(CouponHistoryParam couponHistoryParam) {
        CouponHistory model = couponHistoryMapper.selectOne(
                new QueryWrapper<CouponHistory>()
                    .eq("id",  couponHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(couponHistoryParam.getId());
        model.setCouponId(couponHistoryParam.getCouponId());
        model.setMemberId(couponHistoryParam.getMemberId());
        model.setMemberNickName(couponHistoryParam.getMemberNickName());
        model.setGetType(couponHistoryParam.getGetType());
        model.setUseType(couponHistoryParam.getUseType());
        model.setUseTime(couponHistoryParam.getUseTime());
        model.setOrderId(couponHistoryParam.getOrderId());
        model.setOrderSn(couponHistoryParam.getOrderSn());
        couponHistoryMapper.updateById(model);
    }

    /**
     * 优惠券领取历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        CouponHistory model = couponHistoryMapper.selectOne(
                new QueryWrapper<CouponHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        couponHistoryMapper.delete(new QueryWrapper<CouponHistory>().eq("id", id));
    }

}
