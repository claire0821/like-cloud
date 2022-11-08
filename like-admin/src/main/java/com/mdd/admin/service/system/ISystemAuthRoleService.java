package com.mdd.admin.service.system;

import com.mdd.common.validate.PageParam;
import com.mdd.admin.validate.system.SystemAuthRoleParam;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
import com.mdd.common.core.PageResult;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * 系统角色服务接口类
 */
public interface ISystemAuthRoleService {

    /**
     * 角色所有
     *
     * @author fzr
     * @return List<SystemAuthRoleVo>
     */
    List<Map<String, Object>> all();

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 参数
     * @return PageResult<SysRoleListVo>
     */
    PageResult<SystemAuthRoleVo> list(@Validated PageParam pageParam);

    /**
     * 角色详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysRole
     */
    SystemAuthRoleVo detail(Integer id);

    /**
     * 角色新增
     *
     * @author fzr
     * @param systemAuthRoleParam 参数
     */
    void add(SystemAuthRoleParam systemAuthRoleParam);

    /**
     * 角色更新
     *
     * @author fzr
     * @param systemAuthRoleParam 参数
     */
    void edit(SystemAuthRoleParam systemAuthRoleParam);

    /**
     * 角色删除
     *
     * @author fzr
     * @param id 主键参数
     */
    void del(Integer id);

}
