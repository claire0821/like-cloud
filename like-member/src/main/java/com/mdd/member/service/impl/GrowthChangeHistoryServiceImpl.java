package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IGrowthChangeHistoryService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.GrowthChangeHistoryParam;
import com.mdd.member.vo.GrowthChangeHistoryListVo;
import com.mdd.member.vo.GrowthChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.GrowthChangeHistory;
import com.mdd.member.mapper.GrowthChangeHistoryMapper;
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
 * 成长值变化历史记录实现类
 */
@Service
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryMapper,GrowthChangeHistory> implements IGrowthChangeHistoryService {
        
    @Resource
    GrowthChangeHistoryMapper growthChangeHistoryMapper;

    /**
     * 成长值变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<GrowthChangeHistoryListVo>
     */
    @Override
    public PageResult<GrowthChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<GrowthChangeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        growthChangeHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:changeCount@change_count:int",
            "=:note:str",
            "=:sourceType@source_type:int",
        });

        IPage<GrowthChangeHistory> iPage = growthChangeHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<GrowthChangeHistoryListVo> list = new LinkedList<>();
        for(GrowthChangeHistory item : iPage.getRecords()) {
            GrowthChangeHistoryListVo vo = new GrowthChangeHistoryListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 成长值变化历史记录详情
     *
     * @param id 主键参数
     * @return GrowthChangeHistory
     */
    @Override
    public GrowthChangeHistoryDetailVo detail(Long id) {
        GrowthChangeHistory model = growthChangeHistoryMapper.selectOne(
                new QueryWrapper<GrowthChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        GrowthChangeHistoryDetailVo vo = new GrowthChangeHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 成长值变化历史记录新增
     *
     * @param growthChangeHistoryParam 参数
     */
    @Override
    public void add(GrowthChangeHistoryParam growthChangeHistoryParam) {
        GrowthChangeHistory model = new GrowthChangeHistory();
        model.setMemberId(growthChangeHistoryParam.getMemberId());
        model.setCreateTime(new Date());
        model.setChangeCount(growthChangeHistoryParam.getChangeCount());
        model.setNote(growthChangeHistoryParam.getNote());
        model.setSourceType(growthChangeHistoryParam.getSourceType());
        growthChangeHistoryMapper.insert(model);
    }

    /**
     * 成长值变化历史记录编辑
     *
     * @param growthChangeHistoryParam 参数
     */
    @Override
    public void edit(GrowthChangeHistoryParam growthChangeHistoryParam) {
        GrowthChangeHistory model = growthChangeHistoryMapper.selectOne(
                new QueryWrapper<GrowthChangeHistory>()
                    .eq("id",  growthChangeHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(growthChangeHistoryParam.getId());
        model.setMemberId(growthChangeHistoryParam.getMemberId());
        model.setChangeCount(growthChangeHistoryParam.getChangeCount());
        model.setNote(growthChangeHistoryParam.getNote());
        model.setSourceType(growthChangeHistoryParam.getSourceType());
        growthChangeHistoryMapper.updateById(model);
    }

    /**
     * 成长值变化历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        GrowthChangeHistory model = growthChangeHistoryMapper.selectOne(
                new QueryWrapper<GrowthChangeHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        growthChangeHistoryMapper.delete(new QueryWrapper<GrowthChangeHistory>().eq("id", id));
    }

}
