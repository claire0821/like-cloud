package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.MemberCollectSubject;
import com.mdd.member.validate.MemberCollectSubjectParam;
import com.mdd.member.vo.MemberCollectSubjectListVo;
import com.mdd.member.vo.MemberCollectSubjectDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员收藏的专题活动服务接口类
 */
public interface IMemberCollectSubjectService extends IService<MemberCollectSubject> {

    /**
     * 会员收藏的专题活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberCollectSubjectVo>
     */
    PageResult<MemberCollectSubjectListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员收藏的专题活动详情
     *
     * @param id 主键ID
     * @return MemberCollectSubject
     */
    MemberCollectSubjectDetailVo detail(Long id);

    /**
     * 会员收藏的专题活动新增
     *
     * @param memberCollectSubjectParam 参数
     */
    void add(MemberCollectSubjectParam memberCollectSubjectParam);

    /**
     * 会员收藏的专题活动编辑
     *
     * @param memberCollectSubjectParam 参数
     */
    void edit(MemberCollectSubjectParam memberCollectSubjectParam);

    /**
     * 会员收藏的专题活动删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
