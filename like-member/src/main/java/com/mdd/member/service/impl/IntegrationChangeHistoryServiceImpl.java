package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IIntegrationChangeHistoryService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.IntegrationChangeHistoryParam;
import com.mdd.member.vo.IntegrationChangeHistoryListVo;
import com.mdd.member.vo.IntegrationChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.IntegrationChangeHistory;
import com.mdd.member.mapper.IntegrationChangeHistoryMapper;
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
 * 积分变化历史记录实现类
 */
@Service
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryMapper,IntegrationChangeHistory> implements IIntegrationChangeHistoryService {
        
    @Resource
    IntegrationChangeHistoryMapper integrationChangeHistoryMapper;

    /**
     * 积分变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<IntegrationChangeHistoryListVo>
     */
    @Override
    public PageResult<IntegrationChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<IntegrationChangeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        integrationChangeHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:changeCount@change_count:int",
            "=:note:str",
            "=:sourceTyoe@source_tyoe:int",
        });

        IPage<IntegrationChangeHistory> iPage = integrationChangeHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<IntegrationChangeHistoryListVo> list = new LinkedList<>();
        for(IntegrationChangeHistory item : iPage.getRecords()) {
            IntegrationChangeHistoryListVo vo = new IntegrationChangeHistoryListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 积分变化历史记录详情
     *
     * @param id 主键参数
     * @return IntegrationChangeHistory
     */
    @Override
    public IntegrationChangeHistoryDetailVo detail(Long id) {
        IntegrationChangeHistory model = integrationChangeHistoryMapper.selectOne(
                new QueryWrapper<IntegrationChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        IntegrationChangeHistoryDetailVo vo = new IntegrationChangeHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 积分变化历史记录新增
     *
     * @param integrationChangeHistoryParam 参数
     */
    @Override
    public void add(IntegrationChangeHistoryParam integrationChangeHistoryParam) {
        IntegrationChangeHistory model = new IntegrationChangeHistory();
        model.setMemberId(integrationChangeHistoryParam.getMemberId());
        model.setCreateTime(new Date());
        model.setChangeCount(integrationChangeHistoryParam.getChangeCount());
        model.setNote(integrationChangeHistoryParam.getNote());
        model.setSourceTyoe(integrationChangeHistoryParam.getSourceTyoe());
        integrationChangeHistoryMapper.insert(model);
    }

    /**
     * 积分变化历史记录编辑
     *
     * @param integrationChangeHistoryParam 参数
     */
    @Override
    public void edit(IntegrationChangeHistoryParam integrationChangeHistoryParam) {
        IntegrationChangeHistory model = integrationChangeHistoryMapper.selectOne(
                new QueryWrapper<IntegrationChangeHistory>()
                    .eq("id",  integrationChangeHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(integrationChangeHistoryParam.getId());
        model.setMemberId(integrationChangeHistoryParam.getMemberId());
        model.setChangeCount(integrationChangeHistoryParam.getChangeCount());
        model.setNote(integrationChangeHistoryParam.getNote());
        model.setSourceTyoe(integrationChangeHistoryParam.getSourceTyoe());
        integrationChangeHistoryMapper.updateById(model);
    }

    /**
     * 积分变化历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        IntegrationChangeHistory model = integrationChangeHistoryMapper.selectOne(
                new QueryWrapper<IntegrationChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        integrationChangeHistoryMapper.delete(new QueryWrapper<IntegrationChangeHistory>().eq("id", id));
    }

}
