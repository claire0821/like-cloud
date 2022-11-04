package com.mdd.member.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsGrowthChangeHistoryParam;
import com.mdd.member.vo.UmsGrowthChangeHistoryListVo;
import com.mdd.member.vo.UmsGrowthChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 成长值变化历史记录服务接口类
 */
public interface IUmsGrowthChangeHistoryService {

    /**
     * 成长值变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsGrowthChangeHistoryVo>
     */
    PageResult<UmsGrowthChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 成长值变化历史记录详情
     *
     * @param id 主键ID
     * @return UmsGrowthChangeHistory
     */
    UmsGrowthChangeHistoryDetailVo detail(Long id);

    /**
     * 成长值变化历史记录新增
     *
     * @param umsGrowthChangeHistoryParam 参数
     */
    void add(UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam);

    /**
     * 成长值变化历史记录编辑
     *
     * @param umsGrowthChangeHistoryParam 参数
     */
    void edit(UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam);

    /**
     * 成长值变化历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
