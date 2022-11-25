package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IMemberLoginLogService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberLoginLogParam;
import com.mdd.member.vo.MemberLoginLogListVo;
import com.mdd.member.vo.MemberLoginLogDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.MemberLoginLog;
import com.mdd.member.mapper.MemberLoginLogMapper;
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
 * 会员登录记录实现类
 */
@Service
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper,MemberLoginLog> implements IMemberLoginLogService {
        
    @Resource
    MemberLoginLogMapper memberLoginLogMapper;

    /**
     * 会员登录记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberLoginLogListVo>
     */
    @Override
    public PageResult<MemberLoginLogListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberLoginLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberLoginLogMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:ip:str",
            "=:city:str",
            "=:loginType@login_type:int",
        });

        IPage<MemberLoginLog> iPage = memberLoginLogMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberLoginLogListVo> list = new LinkedList<>();
        for(MemberLoginLog item : iPage.getRecords()) {
            MemberLoginLogListVo vo = new MemberLoginLogListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员登录记录详情
     *
     * @param id 主键参数
     * @return MemberLoginLog
     */
    @Override
    public MemberLoginLogDetailVo detail(Long id) {
        MemberLoginLog model = memberLoginLogMapper.selectOne(
                new QueryWrapper<MemberLoginLog>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberLoginLogDetailVo vo = new MemberLoginLogDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员登录记录新增
     *
     * @param memberLoginLogParam 参数
     */
    @Override
    public void add(MemberLoginLogParam memberLoginLogParam) {
        MemberLoginLog model = new MemberLoginLog();
        model.setMemberId(memberLoginLogParam.getMemberId());
        model.setCreateTime(new Date());
        model.setIp(memberLoginLogParam.getIp());
        model.setCity(memberLoginLogParam.getCity());
        model.setLoginType(memberLoginLogParam.getLoginType());
        memberLoginLogMapper.insert(model);
    }

    /**
     * 会员登录记录编辑
     *
     * @param memberLoginLogParam 参数
     */
    @Override
    public void edit(MemberLoginLogParam memberLoginLogParam) {
        MemberLoginLog model = memberLoginLogMapper.selectOne(
                new QueryWrapper<MemberLoginLog>()
                    .eq("id",  memberLoginLogParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberLoginLogParam.getId());
        model.setMemberId(memberLoginLogParam.getMemberId());
        model.setIp(memberLoginLogParam.getIp());
        model.setCity(memberLoginLogParam.getCity());
        model.setLoginType(memberLoginLogParam.getLoginType());
        memberLoginLogMapper.updateById(model);
    }

    /**
     * 会员登录记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberLoginLog model = memberLoginLogMapper.selectOne(
                new QueryWrapper<MemberLoginLog>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberLoginLogMapper.delete(new QueryWrapper<MemberLoginLog>().eq("id", id));
    }

}
