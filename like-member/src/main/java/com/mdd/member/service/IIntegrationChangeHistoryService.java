package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.IntegrationChangeHistory;
import com.mdd.member.validate.IntegrationChangeHistoryParam;
import com.mdd.member.vo.IntegrationChangeHistoryListVo;
import com.mdd.member.vo.IntegrationChangeHistoryDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 积分变化历史记录服务接口类
 */
public interface IIntegrationChangeHistoryService extends IService<IntegrationChangeHistory> {

    /**
     * 积分变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<IntegrationChangeHistoryVo>
     */
    PageResult<IntegrationChangeHistoryListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 积分变化历史记录详情
     *
     * @param id 主键ID
     * @return IntegrationChangeHistory
     */
    IntegrationChangeHistoryDetailVo detail(Long id);

    /**
     * 积分变化历史记录新增
     *
     * @param integrationChangeHistoryParam 参数
     */
    void add(IntegrationChangeHistoryParam integrationChangeHistoryParam);

    /**
     * 积分变化历史记录编辑
     *
     * @param integrationChangeHistoryParam 参数
     */
    void edit(IntegrationChangeHistoryParam integrationChangeHistoryParam);

    /**
     * 积分变化历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
