package com.mdd.admin.service.notice_user;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.notice_user.UserSystemNoticeParam;
import com.mdd.admin.vo.notice_user.UserSystemNoticeListVo;
import com.mdd.admin.vo.notice_user.UserSystemNoticeDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 用户系统通知服务接口类
 * @author Claire
 */
public interface IUserSystemNoticeService {

    /**
     * 用户系统通知列表
     *
     * @author Claire
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UserSystemNoticeVo>
     */
    PageResult<UserSystemNoticeListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 用户系统通知详情
     *
     * @author Claire
     * @param id 主键ID
     * @return UserSystemNotice
     */
    UserSystemNoticeDetailVo detail(Integer id);

    /**
     * 用户系统通知新增
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     */
    void add(UserSystemNoticeParam userSystemNoticeParam);

    /**
     * 用户系统通知编辑
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     */
    void edit(UserSystemNoticeParam userSystemNoticeParam);

    /**
     * 用户系统通知删除
     *
     * @author Claire
     * @param id 主键ID
     */
    void del(Integer id);

}
