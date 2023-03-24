package com.mdd.common.vo;

import com.mdd.common.config.GlobalConfig;
import com.mdd.common.constant.OrderConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-11 10:34
 **/
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    Long id;
    OrderConstant.OperateManTypeEnum type;//OperateManTypeEnum 用户类型
    String nickname;       // 用户账号
    String username;       // 用户昵称
    Integer role;          // 角色主键
    Set<String> roles;          // 角色
    /**
     * 获取登录id 用户类型:用户ID:权限名称
     */
    public String getLoginId() {
        if (type == null) {
            throw new IllegalArgumentException("用户类型不能为空");
        }
        if (id == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        String res = type.getMsg() + GlobalConfig.LOGINID_JOIN_CODE + id;

        if(roles != null && roles.size() > 0) {
            res = res + GlobalConfig.LOGINID_JOIN_CODE + String.join(",", roles);
        }
        return res;
    }
}
