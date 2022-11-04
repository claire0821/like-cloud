package com.mdd.member.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberCollectSubjectParam;
import com.mdd.member.vo.UmsMemberCollectSubjectListVo;
import com.mdd.member.vo.UmsMemberCollectSubjectDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员收藏的专题活动服务接口类
 */
public interface IUmsMemberCollectSubjectService {

    /**
     * 会员收藏的专题活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberCollectSubjectVo>
     */
    PageResult<UmsMemberCollectSubjectListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员收藏的专题活动详情
     *
     * @param id 主键ID
     * @return UmsMemberCollectSubject
     */
    UmsMemberCollectSubjectDetailVo detail(Long id);

    /**
     * 会员收藏的专题活动新增
     *
     * @param umsMemberCollectSubjectParam 参数
     */
    void add(UmsMemberCollectSubjectParam umsMemberCollectSubjectParam);

    /**
     * 会员收藏的专题活动编辑
     *
     * @param umsMemberCollectSubjectParam 参数
     */
    void edit(UmsMemberCollectSubjectParam umsMemberCollectSubjectParam);

    /**
     * 会员收藏的专题活动删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
