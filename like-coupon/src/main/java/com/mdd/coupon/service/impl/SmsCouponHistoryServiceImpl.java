package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsCouponHistoryService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsCouponHistoryParam;
import com.mdd.coupon.vo.SmsCouponHistoryListVo;
import com.mdd.coupon.vo.SmsCouponHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsCouponHistory;
import com.mdd.coupon.mapper.SmsCouponHistoryMapper;
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
 * 优惠券领取历史记录实现类
 */
@Service
public class SmsCouponHistoryServiceImpl implements ISmsCouponHistoryService {
        
    @Resource
    SmsCouponHistoryMapper smsCouponHistoryMapper;

    /**
     * 优惠券领取历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponHistoryListVo>
     */
    @Override
    public PageResult<SmsCouponHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsCouponHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsCouponHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:memberId@member_id:long",
            "like:memberNickName@member_nick_name:str",
            "=:getType@get_type:int",
            "=:useType@use_type:int",
            "=:useTime@use_time:str",
            "=:orderId@order_id:long",
            "=:orderSn@order_sn:long",
        });

        IPage<SmsCouponHistory> iPage = smsCouponHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsCouponHistoryListVo> list = new LinkedList<>();
        for(SmsCouponHistory item : iPage.getRecords()) {
            SmsCouponHistoryListVo vo = new SmsCouponHistoryListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券领取历史记录详情
     *
     * @param id 主键参数
     * @return SmsCouponHistory
     */
    @Override
    public SmsCouponHistoryDetailVo detail(Long id) {
        SmsCouponHistory model = smsCouponHistoryMapper.selectOne(
                new QueryWrapper<SmsCouponHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsCouponHistoryDetailVo vo = new SmsCouponHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券领取历史记录新增
     *
     * @param smsCouponHistoryParam 参数
     */
    @Override
    public void add(SmsCouponHistoryParam smsCouponHistoryParam) {
        SmsCouponHistory model = new SmsCouponHistory();
        model.setCouponId(smsCouponHistoryParam.getCouponId());
        model.setMemberId(smsCouponHistoryParam.getMemberId());
        model.setMemberNickName(smsCouponHistoryParam.getMemberNickName());
        model.setGetType(smsCouponHistoryParam.getGetType());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUseType(smsCouponHistoryParam.getUseType());
        model.setUseTime(smsCouponHistoryParam.getUseTime());
        model.setOrderId(smsCouponHistoryParam.getOrderId());
        model.setOrderSn(smsCouponHistoryParam.getOrderSn());
        smsCouponHistoryMapper.insert(model);
    }

    /**
     * 优惠券领取历史记录编辑
     *
     * @param smsCouponHistoryParam 参数
     */
    @Override
    public void edit(SmsCouponHistoryParam smsCouponHistoryParam) {
        SmsCouponHistory model = smsCouponHistoryMapper.selectOne(
                new QueryWrapper<SmsCouponHistory>()
                    .eq("id",  smsCouponHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsCouponHistoryParam.getId());
        model.setCouponId(smsCouponHistoryParam.getCouponId());
        model.setMemberId(smsCouponHistoryParam.getMemberId());
        model.setMemberNickName(smsCouponHistoryParam.getMemberNickName());
        model.setGetType(smsCouponHistoryParam.getGetType());
        model.setUseType(smsCouponHistoryParam.getUseType());
        model.setUseTime(smsCouponHistoryParam.getUseTime());
        model.setOrderId(smsCouponHistoryParam.getOrderId());
        model.setOrderSn(smsCouponHistoryParam.getOrderSn());
        smsCouponHistoryMapper.updateById(model);
    }

    /**
     * 优惠券领取历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsCouponHistory model = smsCouponHistoryMapper.selectOne(
                new QueryWrapper<SmsCouponHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsCouponHistoryMapper.delete(new QueryWrapper<SmsCouponHistory>().eq("id", id));
    }

}
