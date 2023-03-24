package com.mdd.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mdd.auth.feign.AdminFeignService;
import com.mdd.auth.feign.MemberFeignService;
import com.mdd.common.constant.LoginConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.LoginException;
import com.mdd.auth.utils.LoginHelper;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: server
 * @description: 登录校验方法
 * @author: Claire
 * @create: 2023-03-22 09:37
 **/
@Service
public class LoginService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    AdminFeignService adminFeignService;

    /**
     * 登录
     */
    public String login(LoginParam loginParam) {
        AjaxResult<UserVo> login = null;
        if(loginParam.getAdmin()) {
            login = adminFeignService.login(loginParam);
        } else {
            login = memberFeignService.login(loginParam);
        }
        if(ObjectUtil.isEmpty(login)) {
            throw new LoginException(HttpEnum.LOGIN_ERROR.getCode(),HttpEnum.LOGIN_ERROR.getMsg());
        }
        // 获取登录token
        final UserVo userInfo = login.getData();
        LoginHelper.loginByDevice(userInfo, LoginConstant.DeviceTypeEnum.PC);
        return StpUtil.getTokenValue();
    }

}
