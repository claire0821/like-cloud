package com.mdd.member.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberStatisticsInfoParam;
import com.mdd.member.vo.UmsMemberStatisticsInfoListVo;
import com.mdd.member.vo.UmsMemberStatisticsInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员统计信息服务接口类
 */
public interface IUmsMemberStatisticsInfoService {

    /**
     * 会员统计信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberStatisticsInfoVo>
     */
    PageResult<UmsMemberStatisticsInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员统计信息详情
     *
     * @param id 主键ID
     * @return UmsMemberStatisticsInfo
     */
    UmsMemberStatisticsInfoDetailVo detail(Long id);

    /**
     * 会员统计信息新增
     *
     * @param umsMemberStatisticsInfoParam 参数
     */
    void add(UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam);

    /**
     * 会员统计信息编辑
     *
     * @param umsMemberStatisticsInfoParam 参数
     */
    void edit(UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam);

    /**
     * 会员统计信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
