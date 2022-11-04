package com.mdd.admin.service.notice_user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.notice_user.IUserSystemNoticeService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.notice_user.UserSystemNoticeParam;
import com.mdd.admin.vo.notice_user.UserSystemNoticeListVo;
import com.mdd.admin.vo.notice_user.UserSystemNoticeDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.notice_user.UserSystemNotice;
import com.mdd.common.mapper.notice_user.UserSystemNoticeMapper;
import com.mdd.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户系统通知实现类
 * @author Claire
 */
@Service
public class UserSystemNoticeServiceImpl implements IUserSystemNoticeService {
        
    @Resource
    UserSystemNoticeMapper userSystemNoticeMapper;

    /**
     * 用户系统通知列表
     *
     * @author Claire
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UserSystemNoticeListVo>
     */
    @Override
    public PageResult<UserSystemNoticeListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UserSystemNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        userSystemNoticeMapper.setSearch(queryWrapper, params, new String[]{
            "=:readState@read_state:int",
            "=:pushState@push_state:int",
            "=:systemNoticeId@system_notice_id:int",
            "=:readTime@read_time:long",
            "=:pullTime@pull_time:long",
        });

        IPage<UserSystemNotice> iPage = userSystemNoticeMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UserSystemNoticeListVo> list = new LinkedList<>();
        for(UserSystemNotice item : iPage.getRecords()) {
            UserSystemNoticeListVo vo = new UserSystemNoticeListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(item.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 用户系统通知详情
     *
     * @author Claire
     * @param id 主键参数
     * @return UserSystemNotice
     */
    @Override
    public UserSystemNoticeDetailVo detail(Integer id) {
        UserSystemNotice model = userSystemNoticeMapper.selectOne(
                new QueryWrapper<UserSystemNotice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UserSystemNoticeDetailVo vo = new UserSystemNoticeDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 用户系统通知新增
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     */
    @Override
    public void add(UserSystemNoticeParam userSystemNoticeParam) {
        UserSystemNotice model = new UserSystemNotice();
        model.setReadState(userSystemNoticeParam.getReadState());
        model.setPushState(userSystemNoticeParam.getPushState());
        model.setSystemNoticeId(userSystemNoticeParam.getSystemNoticeId());
        model.setReadTime(userSystemNoticeParam.getReadTime());
        model.setPullTime(userSystemNoticeParam.getPullTime());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        userSystemNoticeMapper.insert(model);
    }

    /**
     * 用户系统通知编辑
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     */
    @Override
    public void edit(UserSystemNoticeParam userSystemNoticeParam) {
        UserSystemNotice model = userSystemNoticeMapper.selectOne(
                new QueryWrapper<UserSystemNotice>()
                    .eq("id",  userSystemNoticeParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(userSystemNoticeParam.getId());
        model.setReadState(userSystemNoticeParam.getReadState());
        model.setPushState(userSystemNoticeParam.getPushState());
        model.setSystemNoticeId(userSystemNoticeParam.getSystemNoticeId());
        model.setReadTime(userSystemNoticeParam.getReadTime());
        model.setPullTime(userSystemNoticeParam.getPullTime());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        userSystemNoticeMapper.updateById(model);
    }

    /**
     * 用户系统通知删除
     *
     * @author Claire
     * @param id 主键ID
     */
    @Override
    public void del(Integer id) {
        UserSystemNotice model = userSystemNoticeMapper.selectOne(
                new QueryWrapper<UserSystemNotice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        userSystemNoticeMapper.delete(new QueryWrapper<UserSystemNotice>().eq("id", id));
    }

}
