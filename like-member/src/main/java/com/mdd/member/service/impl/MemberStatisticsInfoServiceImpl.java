package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IMemberStatisticsInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberStatisticsInfoParam;
import com.mdd.member.vo.MemberStatisticsInfoListVo;
import com.mdd.member.vo.MemberStatisticsInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.MemberStatisticsInfo;
import com.mdd.member.mapper.MemberStatisticsInfoMapper;
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
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoMapper,MemberStatisticsInfo> implements IMemberStatisticsInfoService {
        
    @Resource
    MemberStatisticsInfoMapper memberStatisticsInfoMapper;

    /**
     * 会员统计信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberStatisticsInfoListVo>
     */
    @Override
    public PageResult<MemberStatisticsInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberStatisticsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberStatisticsInfoMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:consumeAmount@consume_amount:str",
            "=:couponAmount@coupon_amount:str",
            "=:orderCount@order_count:int",
            "=:couponCount@coupon_count:int",
            "=:commentCount@comment_count:int",
            "=:returnOrderCount@return_order_count:int",
            "=:loginCount@login_count:int",
            "=:attendCount@attend_count:int",
            "=:fansCount@fans_count:int",
            "=:collectProductCount@collect_product_count:int",
            "=:collectSubjectCount@collect_subject_count:int",
            "=:collectCommentCount@collect_comment_count:int",
            "=:inviteFriendCount@invite_friend_count:int",
        });

        IPage<MemberStatisticsInfo> iPage = memberStatisticsInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberStatisticsInfoListVo> list = new LinkedList<>();
        for(MemberStatisticsInfo item : iPage.getRecords()) {
            MemberStatisticsInfoListVo vo = new MemberStatisticsInfoListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员统计信息详情
     *
     * @param id 主键参数
     * @return MemberStatisticsInfo
     */
    @Override
    public MemberStatisticsInfoDetailVo detail(Long id) {
        MemberStatisticsInfo model = memberStatisticsInfoMapper.selectOne(
                new QueryWrapper<MemberStatisticsInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberStatisticsInfoDetailVo vo = new MemberStatisticsInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员统计信息新增
     *
     * @param memberStatisticsInfoParam 参数
     */
    @Override
    public void add(MemberStatisticsInfoParam memberStatisticsInfoParam) {
        MemberStatisticsInfo model = new MemberStatisticsInfo();
        model.setMemberId(memberStatisticsInfoParam.getMemberId());
        model.setConsumeAmount(memberStatisticsInfoParam.getConsumeAmount());
        model.setCouponAmount(memberStatisticsInfoParam.getCouponAmount());
        model.setOrderCount(memberStatisticsInfoParam.getOrderCount());
        model.setCouponCount(memberStatisticsInfoParam.getCouponCount());
        model.setCommentCount(memberStatisticsInfoParam.getCommentCount());
        model.setReturnOrderCount(memberStatisticsInfoParam.getReturnOrderCount());
        model.setLoginCount(memberStatisticsInfoParam.getLoginCount());
        model.setAttendCount(memberStatisticsInfoParam.getAttendCount());
        model.setFansCount(memberStatisticsInfoParam.getFansCount());
        model.setCollectProductCount(memberStatisticsInfoParam.getCollectProductCount());
        model.setCollectSubjectCount(memberStatisticsInfoParam.getCollectSubjectCount());
        model.setCollectCommentCount(memberStatisticsInfoParam.getCollectCommentCount());
        model.setInviteFriendCount(memberStatisticsInfoParam.getInviteFriendCount());
        memberStatisticsInfoMapper.insert(model);
    }

    /**
     * 会员统计信息编辑
     *
     * @param memberStatisticsInfoParam 参数
     */
    @Override
    public void edit(MemberStatisticsInfoParam memberStatisticsInfoParam) {
        MemberStatisticsInfo model = memberStatisticsInfoMapper.selectOne(
                new QueryWrapper<MemberStatisticsInfo>()
                    .eq("id",  memberStatisticsInfoParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberStatisticsInfoParam.getId());
        model.setMemberId(memberStatisticsInfoParam.getMemberId());
        model.setConsumeAmount(memberStatisticsInfoParam.getConsumeAmount());
        model.setCouponAmount(memberStatisticsInfoParam.getCouponAmount());
        model.setOrderCount(memberStatisticsInfoParam.getOrderCount());
        model.setCouponCount(memberStatisticsInfoParam.getCouponCount());
        model.setCommentCount(memberStatisticsInfoParam.getCommentCount());
        model.setReturnOrderCount(memberStatisticsInfoParam.getReturnOrderCount());
        model.setLoginCount(memberStatisticsInfoParam.getLoginCount());
        model.setAttendCount(memberStatisticsInfoParam.getAttendCount());
        model.setFansCount(memberStatisticsInfoParam.getFansCount());
        model.setCollectProductCount(memberStatisticsInfoParam.getCollectProductCount());
        model.setCollectSubjectCount(memberStatisticsInfoParam.getCollectSubjectCount());
        model.setCollectCommentCount(memberStatisticsInfoParam.getCollectCommentCount());
        model.setInviteFriendCount(memberStatisticsInfoParam.getInviteFriendCount());
        memberStatisticsInfoMapper.updateById(model);
    }

    /**
     * 会员统计信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberStatisticsInfo model = memberStatisticsInfoMapper.selectOne(
                new QueryWrapper<MemberStatisticsInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberStatisticsInfoMapper.delete(new QueryWrapper<MemberStatisticsInfo>().eq("id", id));
    }

}
