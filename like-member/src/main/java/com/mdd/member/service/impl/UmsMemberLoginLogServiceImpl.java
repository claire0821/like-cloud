package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsMemberLoginLogService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberLoginLogParam;
import com.mdd.member.vo.UmsMemberLoginLogListVo;
import com.mdd.member.vo.UmsMemberLoginLogDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMemberLoginLog;
import com.mdd.member.mapper.UmsMemberLoginLogMapper;
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
 * 会员登录记录实现类
 */
@Service
public class UmsMemberLoginLogServiceImpl implements IUmsMemberLoginLogService {
        
    @Resource
    UmsMemberLoginLogMapper umsMemberLoginLogMapper;

    /**
     * 会员登录记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberLoginLogListVo>
     */
    @Override
    public PageResult<UmsMemberLoginLogListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMemberLoginLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberLoginLogMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:ip:str",
            "=:city:str",
            "=:loginType@login_type:int",
        });

        IPage<UmsMemberLoginLog> iPage = umsMemberLoginLogMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberLoginLogListVo> list = new LinkedList<>();
        for(UmsMemberLoginLog item : iPage.getRecords()) {
            UmsMemberLoginLogListVo vo = new UmsMemberLoginLogListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员登录记录详情
     *
     * @param id 主键参数
     * @return UmsMemberLoginLog
     */
    @Override
    public UmsMemberLoginLogDetailVo detail(Long id) {
        UmsMemberLoginLog model = umsMemberLoginLogMapper.selectOne(
                new QueryWrapper<UmsMemberLoginLog>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberLoginLogDetailVo vo = new UmsMemberLoginLogDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员登录记录新增
     *
     * @param umsMemberLoginLogParam 参数
     */
    @Override
    public void add(UmsMemberLoginLogParam umsMemberLoginLogParam) {
        UmsMemberLoginLog model = new UmsMemberLoginLog();
        model.setMemberId(umsMemberLoginLogParam.getMemberId());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setIp(umsMemberLoginLogParam.getIp());
        model.setCity(umsMemberLoginLogParam.getCity());
        model.setLoginType(umsMemberLoginLogParam.getLoginType());
        umsMemberLoginLogMapper.insert(model);
    }

    /**
     * 会员登录记录编辑
     *
     * @param umsMemberLoginLogParam 参数
     */
    @Override
    public void edit(UmsMemberLoginLogParam umsMemberLoginLogParam) {
        UmsMemberLoginLog model = umsMemberLoginLogMapper.selectOne(
                new QueryWrapper<UmsMemberLoginLog>()
                    .eq("id",  umsMemberLoginLogParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberLoginLogParam.getId());
        model.setMemberId(umsMemberLoginLogParam.getMemberId());
        model.setIp(umsMemberLoginLogParam.getIp());
        model.setCity(umsMemberLoginLogParam.getCity());
        model.setLoginType(umsMemberLoginLogParam.getLoginType());
        umsMemberLoginLogMapper.updateById(model);
    }

    /**
     * 会员登录记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMemberLoginLog model = umsMemberLoginLogMapper.selectOne(
                new QueryWrapper<UmsMemberLoginLog>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberLoginLogMapper.delete(new QueryWrapper<UmsMemberLoginLog>().eq("id", id));
    }

}
