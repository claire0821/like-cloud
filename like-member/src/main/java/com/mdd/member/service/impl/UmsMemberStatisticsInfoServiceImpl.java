package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsMemberStatisticsInfoService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberStatisticsInfoParam;
import com.mdd.member.vo.UmsMemberStatisticsInfoListVo;
import com.mdd.member.vo.UmsMemberStatisticsInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMemberStatisticsInfo;
import com.mdd.member.mapper.UmsMemberStatisticsInfoMapper;
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
 * 会员统计信息实现类
 */
@Service
public class UmsMemberStatisticsInfoServiceImpl implements IUmsMemberStatisticsInfoService {
        
    @Resource
    UmsMemberStatisticsInfoMapper umsMemberStatisticsInfoMapper;

    /**
     * 会员统计信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberStatisticsInfoListVo>
     */
    @Override
    public PageResult<UmsMemberStatisticsInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMemberStatisticsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberStatisticsInfoMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:consumeAmount@consume_amount:str",
            "=:couponAmount@coupon_amount:str",
            "=:orderCount@order_count:long",
            "=:couponCount@coupon_count:long",
            "=:commentCount@comment_count:long",
            "=:returnOrderCount@return_order_count:long",
            "=:loginCount@login_count:long",
            "=:attendCount@attend_count:long",
            "=:fansCount@fans_count:long",
            "=:collectProductCount@collect_product_count:long",
            "=:collectSubjectCount@collect_subject_count:long",
            "=:collectCommentCount@collect_comment_count:long",
            "=:inviteFriendCount@invite_friend_count:long",
        });

        IPage<UmsMemberStatisticsInfo> iPage = umsMemberStatisticsInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberStatisticsInfoListVo> list = new LinkedList<>();
        for(UmsMemberStatisticsInfo item : iPage.getRecords()) {
            UmsMemberStatisticsInfoListVo vo = new UmsMemberStatisticsInfoListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员统计信息详情
     *
     * @param id 主键参数
     * @return UmsMemberStatisticsInfo
     */
    @Override
    public UmsMemberStatisticsInfoDetailVo detail(Long id) {
        UmsMemberStatisticsInfo model = umsMemberStatisticsInfoMapper.selectOne(
                new QueryWrapper<UmsMemberStatisticsInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberStatisticsInfoDetailVo vo = new UmsMemberStatisticsInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员统计信息新增
     *
     * @param umsMemberStatisticsInfoParam 参数
     */
    @Override
    public void add(UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam) {
        UmsMemberStatisticsInfo model = new UmsMemberStatisticsInfo();
        model.setMemberId(umsMemberStatisticsInfoParam.getMemberId());
        model.setConsumeAmount(umsMemberStatisticsInfoParam.getConsumeAmount());
        model.setCouponAmount(umsMemberStatisticsInfoParam.getCouponAmount());
        model.setOrderCount(umsMemberStatisticsInfoParam.getOrderCount());
        model.setCouponCount(umsMemberStatisticsInfoParam.getCouponCount());
        model.setCommentCount(umsMemberStatisticsInfoParam.getCommentCount());
        model.setReturnOrderCount(umsMemberStatisticsInfoParam.getReturnOrderCount());
        model.setLoginCount(umsMemberStatisticsInfoParam.getLoginCount());
        model.setAttendCount(umsMemberStatisticsInfoParam.getAttendCount());
        model.setFansCount(umsMemberStatisticsInfoParam.getFansCount());
        model.setCollectProductCount(umsMemberStatisticsInfoParam.getCollectProductCount());
        model.setCollectSubjectCount(umsMemberStatisticsInfoParam.getCollectSubjectCount());
        model.setCollectCommentCount(umsMemberStatisticsInfoParam.getCollectCommentCount());
        model.setInviteFriendCount(umsMemberStatisticsInfoParam.getInviteFriendCount());
        umsMemberStatisticsInfoMapper.insert(model);
    }

    /**
     * 会员统计信息编辑
     *
     * @param umsMemberStatisticsInfoParam 参数
     */
    @Override
    public void edit(UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam) {
        UmsMemberStatisticsInfo model = umsMemberStatisticsInfoMapper.selectOne(
                new QueryWrapper<UmsMemberStatisticsInfo>()
                    .eq("id",  umsMemberStatisticsInfoParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberStatisticsInfoParam.getId());
        model.setMemberId(umsMemberStatisticsInfoParam.getMemberId());
        model.setConsumeAmount(umsMemberStatisticsInfoParam.getConsumeAmount());
        model.setCouponAmount(umsMemberStatisticsInfoParam.getCouponAmount());
        model.setOrderCount(umsMemberStatisticsInfoParam.getOrderCount());
        model.setCouponCount(umsMemberStatisticsInfoParam.getCouponCount());
        model.setCommentCount(umsMemberStatisticsInfoParam.getCommentCount());
        model.setReturnOrderCount(umsMemberStatisticsInfoParam.getReturnOrderCount());
        model.setLoginCount(umsMemberStatisticsInfoParam.getLoginCount());
        model.setAttendCount(umsMemberStatisticsInfoParam.getAttendCount());
        model.setFansCount(umsMemberStatisticsInfoParam.getFansCount());
        model.setCollectProductCount(umsMemberStatisticsInfoParam.getCollectProductCount());
        model.setCollectSubjectCount(umsMemberStatisticsInfoParam.getCollectSubjectCount());
        model.setCollectCommentCount(umsMemberStatisticsInfoParam.getCollectCommentCount());
        model.setInviteFriendCount(umsMemberStatisticsInfoParam.getInviteFriendCount());
        umsMemberStatisticsInfoMapper.updateById(model);
    }

    /**
     * 会员统计信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMemberStatisticsInfo model = umsMemberStatisticsInfoMapper.selectOne(
                new QueryWrapper<UmsMemberStatisticsInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberStatisticsInfoMapper.delete(new QueryWrapper<UmsMemberStatisticsInfo>().eq("id", id));
    }

}
