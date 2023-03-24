package com.mdd.auth.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.mdd.common.constant.LoginConstant;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @program: server
 * @description: 登录鉴权助手
 * @author: Claire
 * @create: 2023-03-22 09:43
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    /**
     * 登录系统
     *
     * @param loginParam 登录用户信息
     */
//    public static void login(LoginParam loginParam) {
//        loginByDevice(loginParam, null);
//    }

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param userVo 登录用户信息
     */
    public static void loginByDevice(UserVo userVo, LoginConstant.DeviceTypeEnum deviceTypeEnum) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, userVo);
        storage.set(USER_KEY, userVo.getId());
        SaLoginModel model = new SaLoginModel();
        if (ObjectUtil.isNotNull(deviceTypeEnum)) {
            model.setDevice(deviceTypeEnum.getMsg());
        }

        StpUtil.login(userVo.getLoginId(), model.setExtra(USER_KEY, userVo.getId()));
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, userVo);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static UserVo getLoginUser() {
        UserVo userVo = (UserVo) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (userVo != null) {
            return userVo;
        }
        userVo = (UserVo) StpUtil.getTokenSession().get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, userVo);
        return userVo;
    }

    /**
     * 获取用户基于token
     */
    public static UserVo getLoginUser(String token) {
        return (UserVo) StpUtil.getTokenSessionByToken(token).get(LOGIN_USER_KEY);
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        Long userId;
        try {
            userId = Convert.toLong(SaHolder.getStorage().get(USER_KEY));
            if (ObjectUtil.isNull(userId)) {
                userId = Convert.toLong(StpUtil.getExtra(USER_KEY));
                SaHolder.getStorage().set(USER_KEY, userId);
            }
        } catch (Exception e) {
            return null;
        }
        return userId;
    }

    /**
     * 获取部门ID
     */
//    public static Long getDeptId() {
//        return getLoginUser().getDeptId();
//    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户类型
     */
//    public static UserType getUserType() {
//        String loginId = StpUtil.getLoginIdAsString();
//        return UserType.getUserType(loginId);
//    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
//    public static boolean isAdmin(Long userId) {
//        return UserConstants.ADMIN_ID.equals(userId);
//    }

//    public static boolean isAdmin() {
//        return isAdmin(getUserId());
//    }

}
