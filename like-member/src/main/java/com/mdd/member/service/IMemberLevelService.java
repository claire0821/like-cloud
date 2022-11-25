package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.MemberLevel;
import com.mdd.member.validate.MemberLevelParam;
import com.mdd.member.vo.MemberLevelListVo;
import com.mdd.member.vo.MemberLevelDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员等级服务接口类
 */
public interface IMemberLevelService extends IService<MemberLevel> {

    /**
     * 会员等级列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberLevelVo>
     */
    PageResult<MemberLevelListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员等级详情
     *
     * @param id 主键ID
     * @return MemberLevel
     */
    MemberLevelDetailVo detail(Long id);

    /**
     * 会员等级新增
     *
     * @param memberLevelParam 参数
     */
    void add(MemberLevelParam memberLevelParam);

    /**
     * 会员等级编辑
     *
     * @param memberLevelParam 参数
     */
    void edit(MemberLevelParam memberLevelParam);

    /**
     * 会员等级删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    MemberLevel getDefaultLevel();
}
