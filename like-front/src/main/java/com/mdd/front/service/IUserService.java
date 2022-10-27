package com.mdd.front.service;

import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;

import java.util.Map;

/**
 * 用户服务接口类
 */
public interface IUserService {

    /**
     * 个人中心
     *
     * @author fzr
     * @param userId 用户
     * @return UserCenterVo
     */
    UserCenterVo center(Integer userId);

    /**
     * 个人信息
     *
     * @author fzr
     * @param userId 用户ID
     * @return UserInfoVo
     */
    UserInfoVo info(Integer userId);

    /**
     * 编辑信息
     *
     * @author fzr
     * @param params 参数
     * @param userId 用户ID
     */
    void edit(Map<String, String> params, Integer userId);

    /**
     * 修改密码
     *
     * @author fzr
     * @param password 新密码
     * @param oldPassword 旧密码
     * @param userId 用户ID
     */
    void changePwd(String password, String oldPassword, Integer userId);

    /**
     * 绑定手机
     *
     * @author fzr
     * @param params 参数
     * @param userId 用户ID
     */
    void bindMobile(Map<String, String> params, Integer userId);

    /**
     * 微信手机
     *
     * @author fzr
     * @param code 获取手机号的Code
     */
    void mnpMobile(String code);

}
