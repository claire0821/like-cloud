package com.mdd.member.service;

import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.UmsMemberParam;
import com.mdd.member.vo.UmsMemberListVo;
import com.mdd.member.vo.UmsMemberDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员服务接口类
 */
public interface IUmsMemberService {

    /**
     * 会员列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberVo>
     */
    PageResult<UmsMemberListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员详情
     *
     * @param id 主键ID
     * @return UmsMember
     */
    UmsMemberDetailVo detail(Long id);

    /**
     * 会员新增
     *
     * @param umsMemberParam 参数
     */
    void add(UmsMemberParam umsMemberParam);

    /**
     * 会员编辑
     *
     * @param umsMemberParam 参数
     */
    void edit(UmsMemberParam umsMemberParam);

    /**
     * 会员删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
