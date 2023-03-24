package com.mdd.admin.controller.system;

import com.mdd.admin.service.system.ISystemLoginService;
import com.mdd.admin.validate.system.SystemLoginParam;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.exception.LoginException;
import com.mdd.common.exception.OperateException;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 系统登录管理
 */
@RestController("systemLoginController")
@RequestMapping("api/system")
public class LoginController {

    @Resource
    ISystemLoginService iSystemLoginService;

    /**
     * 登录系统
     *
     * @author fzr
     * @param loginParam 登录参数
     * @return AjaxResult
     */
    @PostMapping("/login")
    public AjaxResult<UserVo> login(@Validated() @RequestBody LoginParam loginParam) {
//        try {
//
//        } catch (LoginException e) {
//            return AjaxResult.failed(e.getCode(), e.getMsg());
//        } catch (OperateException e) {
//            return AjaxResult.failed(e.getMsg());
//        }
        UserVo userVo = iSystemLoginService.login(loginParam);
        return AjaxResult.success(userVo);
    }

    /**
     * 退出登录
     *
     * @author fzr
     * @param request 请求接口
     * @return AjaxResult
     */
    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request) {
        try {
            iSystemLoginService.logout(request.getHeader("token"));
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

}
