package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IMemberLevelService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberLevelParam;
import com.mdd.member.vo.MemberLevelListVo;
import com.mdd.member.vo.MemberLevelDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.MemberLevel;
import com.mdd.member.mapper.MemberLevelMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 会员等级实现类
 */
@Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper,MemberLevel> implements IMemberLevelService {
        
    @Resource
    MemberLevelMapper memberLevelMapper;

    /**
     * 会员等级列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberLevelListVo>
     */
    @Override
    public PageResult<MemberLevelListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberLevelMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:growthPoint@growth_point:int",
            "=:defaultStatus@default_status:int",
            "=:freeFreightPoint@free_freight_point:str",
            "=:commentGrowthPoint@comment_growth_point:int",
            "=:priviledgeFreeFreight@priviledge_free_freight:int",
            "=:priviledgeMemberPrice@priviledge_member_price:int",
            "=:priviledgeBirthday@priviledge_birthday:int",
            "=:note:str",
        });

        IPage<MemberLevel> iPage = memberLevelMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberLevelListVo> list = new LinkedList<>();
        for(MemberLevel item : iPage.getRecords()) {
            MemberLevelListVo vo = new MemberLevelListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员等级详情
     *
     * @param id 主键参数
     * @return MemberLevel
     */
    @Override
    public MemberLevelDetailVo detail(Long id) {
        MemberLevel model = memberLevelMapper.selectOne(
                new QueryWrapper<MemberLevel>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberLevelDetailVo vo = new MemberLevelDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员等级新增
     *
     * @param memberLevelParam 参数
     */
    @Override
    public void add(MemberLevelParam memberLevelParam) {
        MemberLevel model = new MemberLevel();
        model.setName(memberLevelParam.getName());
        model.setGrowthPoint(memberLevelParam.getGrowthPoint());
        model.setDefaultStatus(memberLevelParam.getDefaultStatus());
        model.setFreeFreightPoint(memberLevelParam.getFreeFreightPoint());
        model.setCommentGrowthPoint(memberLevelParam.getCommentGrowthPoint());
        model.setPriviledgeFreeFreight(memberLevelParam.getPriviledgeFreeFreight());
        model.setPriviledgeMemberPrice(memberLevelParam.getPriviledgeMemberPrice());
        model.setPriviledgeBirthday(memberLevelParam.getPriviledgeBirthday());
        model.setNote(memberLevelParam.getNote());
        memberLevelMapper.insert(model);
    }

    /**
     * 会员等级编辑
     *
     * @param memberLevelParam 参数
     */
    @Override
    public void edit(MemberLevelParam memberLevelParam) {
        MemberLevel model = memberLevelMapper.selectOne(
                new QueryWrapper<MemberLevel>()
                    .eq("id",  memberLevelParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberLevelParam.getId());
        model.setName(memberLevelParam.getName());
        model.setGrowthPoint(memberLevelParam.getGrowthPoint());
        model.setDefaultStatus(memberLevelParam.getDefaultStatus());
        model.setFreeFreightPoint(memberLevelParam.getFreeFreightPoint());
        model.setCommentGrowthPoint(memberLevelParam.getCommentGrowthPoint());
        model.setPriviledgeFreeFreight(memberLevelParam.getPriviledgeFreeFreight());
        model.setPriviledgeMemberPrice(memberLevelParam.getPriviledgeMemberPrice());
        model.setPriviledgeBirthday(memberLevelParam.getPriviledgeBirthday());
        model.setNote(memberLevelParam.getNote());
        memberLevelMapper.updateById(model);
    }

    /**
     * 会员等级删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberLevel model = memberLevelMapper.selectOne(
                new QueryWrapper<MemberLevel>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberLevelMapper.delete(new QueryWrapper<MemberLevel>().eq("id", id));
    }

    /**
     * 获取默认等级
     * @return
     */
    @Override
    public MemberLevel getDefaultLevel() {
        final MemberLevel default_status = memberLevelMapper.selectOne(new QueryWrapper<MemberLevel>().eq("default_status", 1));
        return default_status;
    }

    @Override
    public List<MemberLevelListVo> getLevel() {
        final List<MemberLevel> memberLevels = memberLevelMapper.selectList(new QueryWrapper<MemberLevel>());
        List<MemberLevelListVo> collect = new ArrayList<>();
        MemberLevelListVo memberLevel = new MemberLevelListVo();
        memberLevel.setId(0L);
        memberLevel.setName("不限等级");
        collect.add(memberLevel);
        collect = memberLevels.stream().map(item -> {
            MemberLevelListVo memberLevelListVo = new MemberLevelListVo();
            memberLevelListVo.setId(item.getId());
            memberLevelListVo.setName(item.getName());
            return memberLevelListVo;
        }).collect(Collectors.toList());
        return collect;
    }

}
