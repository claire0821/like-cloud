package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.MemberLoginLog;
import com.mdd.member.validate.MemberLoginLogParam;
import com.mdd.member.vo.MemberLoginLogListVo;
import com.mdd.member.vo.MemberLoginLogDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员登录记录服务接口类
 */
public interface IMemberLoginLogService extends IService<MemberLoginLog> {

    /**
     * 会员登录记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberLoginLogVo>
     */
    PageResult<MemberLoginLogListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员登录记录详情
     *
     * @param id 主键ID
     * @return MemberLoginLog
     */
    MemberLoginLogDetailVo detail(Long id);

    /**
     * 会员登录记录新增
     *
     * @param memberLoginLogParam 参数
     */
    void add(MemberLoginLogParam memberLoginLogParam);

    /**
     * 会员登录记录编辑
     *
     * @param memberLoginLogParam 参数
     */
    void edit(MemberLoginLogParam memberLoginLogParam);

    /**
     * 会员登录记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
