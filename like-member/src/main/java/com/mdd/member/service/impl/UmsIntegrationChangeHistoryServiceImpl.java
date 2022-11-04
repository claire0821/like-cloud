package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsIntegrationChangeHistoryService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsIntegrationChangeHistoryParam;
import com.mdd.member.vo.UmsIntegrationChangeHistoryListVo;
import com.mdd.member.vo.UmsIntegrationChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsIntegrationChangeHistory;
import com.mdd.member.mapper.UmsIntegrationChangeHistoryMapper;
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
 * 积分变化历史记录实现类
 */
@Service
public class UmsIntegrationChangeHistoryServiceImpl implements IUmsIntegrationChangeHistoryService {
        
    @Resource
    UmsIntegrationChangeHistoryMapper umsIntegrationChangeHistoryMapper;

    /**
     * 积分变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsIntegrationChangeHistoryListVo>
     */
    @Override
    public PageResult<UmsIntegrationChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsIntegrationChangeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsIntegrationChangeHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:changeCount@change_count:long",
            "=:note:str",
            "=:sourceTyoe@source_tyoe:long",
        });

        IPage<UmsIntegrationChangeHistory> iPage = umsIntegrationChangeHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsIntegrationChangeHistoryListVo> list = new LinkedList<>();
        for(UmsIntegrationChangeHistory item : iPage.getRecords()) {
            UmsIntegrationChangeHistoryListVo vo = new UmsIntegrationChangeHistoryListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 积分变化历史记录详情
     *
     * @param id 主键参数
     * @return UmsIntegrationChangeHistory
     */
    @Override
    public UmsIntegrationChangeHistoryDetailVo detail(Long id) {
        UmsIntegrationChangeHistory model = umsIntegrationChangeHistoryMapper.selectOne(
                new QueryWrapper<UmsIntegrationChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsIntegrationChangeHistoryDetailVo vo = new UmsIntegrationChangeHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 积分变化历史记录新增
     *
     * @param umsIntegrationChangeHistoryParam 参数
     */
    @Override
    public void add(UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam) {
        UmsIntegrationChangeHistory model = new UmsIntegrationChangeHistory();
        model.setMemberId(umsIntegrationChangeHistoryParam.getMemberId());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setChangeCount(umsIntegrationChangeHistoryParam.getChangeCount());
        model.setNote(umsIntegrationChangeHistoryParam.getNote());
        model.setSourceTyoe(umsIntegrationChangeHistoryParam.getSourceTyoe());
        umsIntegrationChangeHistoryMapper.insert(model);
    }

    /**
     * 积分变化历史记录编辑
     *
     * @param umsIntegrationChangeHistoryParam 参数
     */
    @Override
    public void edit(UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam) {
        UmsIntegrationChangeHistory model = umsIntegrationChangeHistoryMapper.selectOne(
                new QueryWrapper<UmsIntegrationChangeHistory>()
                    .eq("id",  umsIntegrationChangeHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsIntegrationChangeHistoryParam.getId());
        model.setMemberId(umsIntegrationChangeHistoryParam.getMemberId());
        model.setChangeCount(umsIntegrationChangeHistoryParam.getChangeCount());
        model.setNote(umsIntegrationChangeHistoryParam.getNote());
        model.setSourceTyoe(umsIntegrationChangeHistoryParam.getSourceTyoe());
        umsIntegrationChangeHistoryMapper.updateById(model);
    }

    /**
     * 积分变化历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsIntegrationChangeHistory model = umsIntegrationChangeHistoryMapper.selectOne(
                new QueryWrapper<UmsIntegrationChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsIntegrationChangeHistoryMapper.delete(new QueryWrapper<UmsIntegrationChangeHistory>().eq("id", id));
    }

}
