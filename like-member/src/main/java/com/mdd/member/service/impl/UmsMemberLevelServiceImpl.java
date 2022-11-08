package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.member.service.IUmsMemberLevelService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.UmsMemberLevelParam;
import com.mdd.member.vo.UmsMemberLevelListVo;
import com.mdd.member.vo.UmsMemberLevelDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMemberLevel;
import com.mdd.member.mapper.UmsMemberLevelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 会员等级实现类
 */
@Service
public class UmsMemberLevelServiceImpl implements IUmsMemberLevelService {
        
    @Resource
    UmsMemberLevelMapper umsMemberLevelMapper;

    /**
     * 会员等级列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberLevelListVo>
     */
    @Override
    public PageResult<UmsMemberLevelListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberLevelMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:growthPoint@growth_point:long",
            "=:defaultStatus@default_status:long",
            "=:freeFreightPoint@free_freight_point:str",
            "=:commentGrowthPoint@comment_growth_point:long",
            "=:priviledgeFreeFreight@priviledge_free_freight:long",
            "=:priviledgeMemberPrice@priviledge_member_price:long",
            "=:priviledgeBirthday@priviledge_birthday:long",
            "=:note:str",
        });

        IPage<UmsMemberLevel> iPage = umsMemberLevelMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberLevelListVo> list = new LinkedList<>();
        for(UmsMemberLevel item : iPage.getRecords()) {
            UmsMemberLevelListVo vo = new UmsMemberLevelListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员等级详情
     *
     * @param id 主键参数
     * @return UmsMemberLevel
     */
    @Override
    public UmsMemberLevelDetailVo detail(Long id) {
        UmsMemberLevel model = umsMemberLevelMapper.selectOne(
                new QueryWrapper<UmsMemberLevel>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberLevelDetailVo vo = new UmsMemberLevelDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员等级新增
     *
     * @param umsMemberLevelParam 参数
     */
    @Override
    public void add(UmsMemberLevelParam umsMemberLevelParam) {
        UmsMemberLevel model = new UmsMemberLevel();
        model.setName(umsMemberLevelParam.getName());
        model.setGrowthPoint(umsMemberLevelParam.getGrowthPoint());
        model.setDefaultStatus(umsMemberLevelParam.getDefaultStatus());
        model.setFreeFreightPoint(umsMemberLevelParam.getFreeFreightPoint());
        model.setCommentGrowthPoint(umsMemberLevelParam.getCommentGrowthPoint());
        model.setPriviledgeFreeFreight(umsMemberLevelParam.getPriviledgeFreeFreight());
        model.setPriviledgeMemberPrice(umsMemberLevelParam.getPriviledgeMemberPrice());
        model.setPriviledgeBirthday(umsMemberLevelParam.getPriviledgeBirthday());
        model.setNote(umsMemberLevelParam.getNote());
        umsMemberLevelMapper.insert(model);
    }

    /**
     * 会员等级编辑
     *
     * @param umsMemberLevelParam 参数
     */
    @Override
    public void edit(UmsMemberLevelParam umsMemberLevelParam) {
        UmsMemberLevel model = umsMemberLevelMapper.selectOne(
                new QueryWrapper<UmsMemberLevel>()
                    .eq("id",  umsMemberLevelParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberLevelParam.getId());
        model.setName(umsMemberLevelParam.getName());
        model.setGrowthPoint(umsMemberLevelParam.getGrowthPoint());
        model.setDefaultStatus(umsMemberLevelParam.getDefaultStatus());
        model.setFreeFreightPoint(umsMemberLevelParam.getFreeFreightPoint());
        model.setCommentGrowthPoint(umsMemberLevelParam.getCommentGrowthPoint());
        model.setPriviledgeFreeFreight(umsMemberLevelParam.getPriviledgeFreeFreight());
        model.setPriviledgeMemberPrice(umsMemberLevelParam.getPriviledgeMemberPrice());
        model.setPriviledgeBirthday(umsMemberLevelParam.getPriviledgeBirthday());
        model.setNote(umsMemberLevelParam.getNote());
        umsMemberLevelMapper.updateById(model);
    }

    /**
     * 会员等级删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMemberLevel model = umsMemberLevelMapper.selectOne(
                new QueryWrapper<UmsMemberLevel>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberLevelMapper.delete(new QueryWrapper<UmsMemberLevel>().eq("id", id));
    }

}
