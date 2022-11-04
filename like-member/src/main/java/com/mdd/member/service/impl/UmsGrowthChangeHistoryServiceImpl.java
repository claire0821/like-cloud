package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsGrowthChangeHistoryService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsGrowthChangeHistoryParam;
import com.mdd.member.vo.UmsGrowthChangeHistoryListVo;
import com.mdd.member.vo.UmsGrowthChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsGrowthChangeHistory;
import com.mdd.member.mapper.UmsGrowthChangeHistoryMapper;
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
 * 成长值变化历史记录实现类
 */
@Service
public class UmsGrowthChangeHistoryServiceImpl implements IUmsGrowthChangeHistoryService {
        
    @Resource
    UmsGrowthChangeHistoryMapper umsGrowthChangeHistoryMapper;

    /**
     * 成长值变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsGrowthChangeHistoryListVo>
     */
    @Override
    public PageResult<UmsGrowthChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsGrowthChangeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsGrowthChangeHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:changeCount@change_count:long",
            "=:note:str",
            "=:sourceType@source_type:long",
        });

        IPage<UmsGrowthChangeHistory> iPage = umsGrowthChangeHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsGrowthChangeHistoryListVo> list = new LinkedList<>();
        for(UmsGrowthChangeHistory item : iPage.getRecords()) {
            UmsGrowthChangeHistoryListVo vo = new UmsGrowthChangeHistoryListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 成长值变化历史记录详情
     *
     * @param id 主键参数
     * @return UmsGrowthChangeHistory
     */
    @Override
    public UmsGrowthChangeHistoryDetailVo detail(Long id) {
        UmsGrowthChangeHistory model = umsGrowthChangeHistoryMapper.selectOne(
                new QueryWrapper<UmsGrowthChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsGrowthChangeHistoryDetailVo vo = new UmsGrowthChangeHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 成长值变化历史记录新增
     *
     * @param umsGrowthChangeHistoryParam 参数
     */
    @Override
    public void add(UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam) {
        UmsGrowthChangeHistory model = new UmsGrowthChangeHistory();
        model.setMemberId(umsGrowthChangeHistoryParam.getMemberId());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setChangeCount(umsGrowthChangeHistoryParam.getChangeCount());
        model.setNote(umsGrowthChangeHistoryParam.getNote());
        model.setSourceType(umsGrowthChangeHistoryParam.getSourceType());
        umsGrowthChangeHistoryMapper.insert(model);
    }

    /**
     * 成长值变化历史记录编辑
     *
     * @param umsGrowthChangeHistoryParam 参数
     */
    @Override
    public void edit(UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam) {
        UmsGrowthChangeHistory model = umsGrowthChangeHistoryMapper.selectOne(
                new QueryWrapper<UmsGrowthChangeHistory>()
                    .eq("id",  umsGrowthChangeHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsGrowthChangeHistoryParam.getId());
        model.setMemberId(umsGrowthChangeHistoryParam.getMemberId());
        model.setChangeCount(umsGrowthChangeHistoryParam.getChangeCount());
        model.setNote(umsGrowthChangeHistoryParam.getNote());
        model.setSourceType(umsGrowthChangeHistoryParam.getSourceType());
        umsGrowthChangeHistoryMapper.updateById(model);
    }

    /**
     * 成长值变化历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsGrowthChangeHistory model = umsGrowthChangeHistoryMapper.selectOne(
                new QueryWrapper<UmsGrowthChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsGrowthChangeHistoryMapper.delete(new QueryWrapper<UmsGrowthChangeHistory>().eq("id", id));
    }

}
