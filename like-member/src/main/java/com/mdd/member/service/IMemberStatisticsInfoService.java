package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.MemberStatisticsInfo;
import com.mdd.member.validate.MemberStatisticsInfoParam;
import com.mdd.member.vo.MemberStatisticsInfoListVo;
import com.mdd.member.vo.MemberStatisticsInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员统计信息服务接口类
 */
public interface IMemberStatisticsInfoService extends IService<MemberStatisticsInfo> {

    /**
     * 会员统计信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberStatisticsInfoVo>
     */
    PageResult<MemberStatisticsInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员统计信息详情
     *
     * @param id 主键ID
     * @return MemberStatisticsInfo
     */
    MemberStatisticsInfoDetailVo detail(Long id);

    /**
     * 会员统计信息新增
     *
     * @param memberStatisticsInfoParam 参数
     */
    void add(MemberStatisticsInfoParam memberStatisticsInfoParam);

    /**
     * 会员统计信息编辑
     *
     * @param memberStatisticsInfoParam 参数
     */
    void edit(MemberStatisticsInfoParam memberStatisticsInfoParam);

    /**
     * 会员统计信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
