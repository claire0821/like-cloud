package com.mdd.admin.service.system;

import java.util.List;

/**
 * 系统角色菜单服务接口类
 */
public interface ISystemAuthPermService {

    /**
     * 根据角色ID获取菜单ID
     *
     * @param roleId 角色ID
     * @return List<Integer>
     */
    List<Integer> selectMenuIdsByRoleId(Integer roleId);

    /**
     * 批量写入角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     * @param menuIds 菜单ID组
     */
    void batchSaveByMenuIds(Integer roleId, String menuIds);

    /**
     * 根据角色ID批量删除角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     */
    void batchDeleteByRoleId(Integer roleId);

    /**
     * 根据菜单ID批量删除角色菜单
     *
     * @author fzr
     * @param menuId 菜单ID
     */
    void batchDeleteByMenuId(Integer menuId);

    /**
     * 缓存角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     */
    void cacheRoleMenusByRoleId(Integer roleId);

}
