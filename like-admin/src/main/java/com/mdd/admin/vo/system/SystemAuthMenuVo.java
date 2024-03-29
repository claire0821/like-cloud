package com.mdd.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统菜单Vo
 */
@Data
public class SystemAuthMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private Integer pid;       // 上级菜单
    private String menuType;   // 权限类型: [M=目录, C=菜单, A=按钮]
    private String menuName;   // 菜单名称
    private String menuIcon;   // 菜单图标
    private Integer menuSort;  // 菜单排序
    private String perms;      // 权限标识
    private String paths;      // 路由地址
    private String component;  // 前端组件
    private String selected;   // 选中路径
    private String params;     // 路由参数
    private Integer isCache;   // 是否缓存: [0=否, 1=是]
    private Integer isShow;    // 是否显示: [0=否, 1=是]
    private Integer isDisable; // 是否禁用: [0=否, 1=是]
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
