package com.mdd.admin.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.admin.validate.system.SystemAuthAdminParam;
import com.mdd.admin.vo.system.SystemAuthAdminVo;
import com.mdd.admin.vo.system.SystemAuthSelfVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.system.SystemAuthAdmin;

import java.util.Map;

/**
 * 系统管理员服务接口类
 */
public interface ISystemAuthAdminService extends IService<SystemAuthAdmin> {

    /**
     * 根据账号查找管理员
     *
     * @author fzr
     * @param username 主键ID
     * @return SysAdmin
     */
    SystemAuthAdmin findByUsername(String username);

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<SysAdminListVo>
     */
    PageResult<SystemAuthAdminVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 当前管理员
     *
     * @author fzr
     * @return SystemSelfVo
     */
    SystemAuthSelfVo self();

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysAdmin
     */
    SystemAuthAdminVo detail(Long id);

    /**
     * 管理员新增
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    void add(SystemAuthAdminParam systemAuthAdminParam);

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    void edit(SystemAuthAdminParam systemAuthAdminParam);

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    void upInfo(SystemAuthAdminParam systemAuthAdminParam, Long adminId);

    /**
     * 管理员删除
     *
     * @author fzr
     * @param id 主键参数
     */
    void del(Long id);

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @param id 主键参数
     */
    void disable(Long id);

    /**
     * 缓存管理员
     */
    void cacheAdminUserByUid(Long id);

}
