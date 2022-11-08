package com.mdd.member.service;

import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.UmsMemberLevelParam;
import com.mdd.member.vo.UmsMemberLevelListVo;
import com.mdd.member.vo.UmsMemberLevelDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员等级服务接口类
 */
public interface IUmsMemberLevelService {

    /**
     * 会员等级列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberLevelVo>
     */
    PageResult<UmsMemberLevelListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员等级详情
     *
     * @param id 主键ID
     * @return UmsMemberLevel
     */
    UmsMemberLevelDetailVo detail(Long id);

    /**
     * 会员等级新增
     *
     * @param umsMemberLevelParam 参数
     */
    void add(UmsMemberLevelParam umsMemberLevelParam);

    /**
     * 会员等级编辑
     *
     * @param umsMemberLevelParam 参数
     */
    void edit(UmsMemberLevelParam umsMemberLevelParam);

    /**
     * 会员等级删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
