package com.mdd.member.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberLoginLogParam;
import com.mdd.member.vo.UmsMemberLoginLogListVo;
import com.mdd.member.vo.UmsMemberLoginLogDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员登录记录服务接口类
 */
public interface IUmsMemberLoginLogService {

    /**
     * 会员登录记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberLoginLogVo>
     */
    PageResult<UmsMemberLoginLogListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员登录记录详情
     *
     * @param id 主键ID
     * @return UmsMemberLoginLog
     */
    UmsMemberLoginLogDetailVo detail(Long id);

    /**
     * 会员登录记录新增
     *
     * @param umsMemberLoginLogParam 参数
     */
    void add(UmsMemberLoginLogParam umsMemberLoginLogParam);

    /**
     * 会员登录记录编辑
     *
     * @param umsMemberLoginLogParam 参数
     */
    void edit(UmsMemberLoginLogParam umsMemberLoginLogParam);

    /**
     * 会员登录记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
