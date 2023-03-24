package com.mdd.admin.service.system;

import com.mdd.admin.validate.system.SystemLoginParam;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;

import java.util.Map;

/**
 * 系统登录服务接口类
 */
public interface ISystemLoginService {

    /**
     * 登录
     *
     * @author fzr
     * @param loginParam 登录参数
     * @return token
     */
    UserVo login(LoginParam loginParam);

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    void logout(String token);

}
