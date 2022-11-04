package com.mdd.member.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsIntegrationChangeHistoryParam;
import com.mdd.member.vo.UmsIntegrationChangeHistoryListVo;
import com.mdd.member.vo.UmsIntegrationChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 积分变化历史记录服务接口类
 */
public interface IUmsIntegrationChangeHistoryService {

    /**
     * 积分变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsIntegrationChangeHistoryVo>
     */
    PageResult<UmsIntegrationChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 积分变化历史记录详情
     *
     * @param id 主键ID
     * @return UmsIntegrationChangeHistory
     */
    UmsIntegrationChangeHistoryDetailVo detail(Long id);

    /**
     * 积分变化历史记录新增
     *
     * @param umsIntegrationChangeHistoryParam 参数
     */
    void add(UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam);

    /**
     * 积分变化历史记录编辑
     *
     * @param umsIntegrationChangeHistoryParam 参数
     */
    void edit(UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam);

    /**
     * 积分变化历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
