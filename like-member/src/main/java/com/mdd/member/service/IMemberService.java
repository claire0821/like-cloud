package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.common.validate.member.LoginParam;
import com.mdd.member.entity.Member;
import com.mdd.member.validate.MemberParam;
import com.mdd.common.validate.member.RegParam;
import com.mdd.member.vo.MemberListVo;
import com.mdd.member.vo.MemberDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员服务接口类
 */
public interface IMemberService extends IService<Member> {

    /**
     * 会员列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberVo>
     */
    PageResult<MemberListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员详情
     *
     * @param id 主键ID
     * @return Member
     */
    MemberDetailVo detail(Long id);

    /**
     * 会员新增
     *
     * @param memberParam 参数
     */
    void add(MemberParam memberParam);

    /**
     * 会员编辑
     *
     * @param memberParam 参数
     */
    void edit(MemberParam memberParam);

    /**
     * 会员删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void register(RegParam regParam);

    void checkPhoneUnique(String phone);

    void checkUserNameUnique(String userName);

    MemberDetailVo login(LoginParam loginParam);

    MemberDetailVo center(Long userId);

    void edit(Map<String, String> params, Long userId);
}
