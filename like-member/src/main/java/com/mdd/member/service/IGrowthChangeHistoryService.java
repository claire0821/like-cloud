package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.GrowthChangeHistory;
import com.mdd.member.validate.GrowthChangeHistoryParam;
import com.mdd.member.vo.GrowthChangeHistoryListVo;
import com.mdd.member.vo.GrowthChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 成长值变化历史记录服务接口类
 */
public interface IGrowthChangeHistoryService extends IService<GrowthChangeHistory> {

    /**
     * 成长值变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<GrowthChangeHistoryVo>
     */
    PageResult<GrowthChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 成长值变化历史记录详情
     *
     * @param id 主键ID
     * @return GrowthChangeHistory
     */
    GrowthChangeHistoryDetailVo detail(Long id);

    /**
     * 成长值变化历史记录新增
     *
     * @param growthChangeHistoryParam 参数
     */
    void add(GrowthChangeHistoryParam growthChangeHistoryParam);

    /**
     * 成长值变化历史记录编辑
     *
     * @param growthChangeHistoryParam 参数
     */
    void edit(GrowthChangeHistoryParam growthChangeHistoryParam);

    /**
     * 成长值变化历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
