package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsMemberService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberParam;
import com.mdd.member.vo.UmsMemberListVo;
import com.mdd.member.vo.UmsMemberDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMember;
import com.mdd.member.mapper.UmsMemberMapper;
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
 * 会员实现类
 */
@Service
public class UmsMemberServiceImpl implements IUmsMemberService {
        
    @Resource
    UmsMemberMapper umsMemberMapper;

    /**
     * 会员列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberListVo>
     */
    @Override
    public PageResult<UmsMemberListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberMapper.setSearch(queryWrapper, params, new String[]{
            "=:levelId@level_id:long",
            "like:username:str",
            "=:password:str",
            "like:nickname:str",
            "like:mobile:str",
            "=:email:str",
            "=:header:str",
            "=:gender:long",
            "=:birth:str",
            "=:city:str",
            "=:job:str",
            "=:sign:str",
            "=:sourceType@source_type:long",
            "=:integration:long",
            "=:growth:long",
            "=:status:long",
        });

        IPage<UmsMember> iPage = umsMemberMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberListVo> list = new LinkedList<>();
        for(UmsMember item : iPage.getRecords()) {
            UmsMemberListVo vo = new UmsMemberListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员详情
     *
     * @param id 主键参数
     * @return UmsMember
     */
    @Override
    public UmsMemberDetailVo detail(Long id) {
        UmsMember model = umsMemberMapper.selectOne(
                new QueryWrapper<UmsMember>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberDetailVo vo = new UmsMemberDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员新增
     *
     * @param umsMemberParam 参数
     */
    @Override
    public void add(UmsMemberParam umsMemberParam) {
        UmsMember model = new UmsMember();
        model.setLevelId(umsMemberParam.getLevelId());
        model.setUsername(umsMemberParam.getUsername());
        model.setPassword(umsMemberParam.getPassword());
        model.setNickname(umsMemberParam.getNickname());
        model.setMobile(umsMemberParam.getMobile());
        model.setEmail(umsMemberParam.getEmail());
        model.setHeader(umsMemberParam.getHeader());
        model.setGender(umsMemberParam.getGender());
        model.setBirth(umsMemberParam.getBirth());
        model.setCity(umsMemberParam.getCity());
        model.setJob(umsMemberParam.getJob());
        model.setSign(umsMemberParam.getSign());
        model.setSourceType(umsMemberParam.getSourceType());
        model.setIntegration(umsMemberParam.getIntegration());
        model.setGrowth(umsMemberParam.getGrowth());
        model.setStatus(umsMemberParam.getStatus());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        umsMemberMapper.insert(model);
    }

    /**
     * 会员编辑
     *
     * @param umsMemberParam 参数
     */
    @Override
    public void edit(UmsMemberParam umsMemberParam) {
        UmsMember model = umsMemberMapper.selectOne(
                new QueryWrapper<UmsMember>()
                    .eq("id",  umsMemberParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberParam.getId());
        model.setLevelId(umsMemberParam.getLevelId());
        model.setUsername(umsMemberParam.getUsername());
        model.setPassword(umsMemberParam.getPassword());
        model.setNickname(umsMemberParam.getNickname());
        model.setMobile(umsMemberParam.getMobile());
        model.setEmail(umsMemberParam.getEmail());
        model.setHeader(umsMemberParam.getHeader());
        model.setGender(umsMemberParam.getGender());
        model.setBirth(umsMemberParam.getBirth());
        model.setCity(umsMemberParam.getCity());
        model.setJob(umsMemberParam.getJob());
        model.setSign(umsMemberParam.getSign());
        model.setSourceType(umsMemberParam.getSourceType());
        model.setIntegration(umsMemberParam.getIntegration());
        model.setGrowth(umsMemberParam.getGrowth());
        model.setStatus(umsMemberParam.getStatus());
        umsMemberMapper.updateById(model);
    }

    /**
     * 会员删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMember model = umsMemberMapper.selectOne(
                new QueryWrapper<UmsMember>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberMapper.delete(new QueryWrapper<UmsMember>().eq("id", id));
    }

}
