package com.mdd.auth.controller;

import com.alibaba.fastjson.TypeReference;
import com.mdd.auth.feign.MemberFeignService;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.LoginException;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.validate.member.LoginParam;
import com.mdd.common.validate.member.RegParam;
import com.mdd.common.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @program: server
 * @description: 登录管理
 * @author: Claire
 * @create: 2022-11-21 17:25
 **/

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Resource
    MemberFeignService memberFeignService;

    /**
     * 注册账号
     *
     * @author fzr
     * @param regParam 参数
     * @return Object
     */
    @PostMapping("/register")
    public Object register(@Validated @RequestBody RegParam regParam) {
        memberFeignService.register(regParam);
        return AjaxResult.success();
    }

    /**
     * 登录验证
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/check")
    public Object check(@RequestBody Map<String, String> params) {
//        Assert.notNull(params.get("scene"), "scene参数缺失!");
        Map<String, Object> map = new LinkedHashMap<>();
//        switch (params.get("scene")) {
//            case "mnp":
//                map = iLoginService.mnpLogin(params);
//                break;
//            case "mobile":
//                map = iLoginService.mobileLogin(params);
//                break;
//            case "account":
//                map = iLoginService.accountLogin(params);
//                break;
//        }
        return AjaxResult.success(map);
    }

    /**
     * 公众号登录
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
//    @GetMapping("/oaLogin")
//    public Object oaLogin(@RequestParam Map<String, String> params) {
//        //Map<String, Object> map = iLoginService.officeLogin(params);
//        return AjaxResult.success(map);
//    }

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return Object
     */
    @GetMapping("/codeUrl")
    public Object codeUrl(@RequestParam String url) {
//        Assert.notNull(url, "url参数不能为空");
//        String uri = iLoginService.codeUrl(url);
        Map<String, String> response = new LinkedHashMap<>();
//        response.put("url", uri);
        return AjaxResult.success(response);
    }

    /**
     * 忘记密码
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/forgotPassword")
    public Object forgotPassword(@RequestBody Map<String, String> params) {
        //iLoginService.forgotPassword(params);
        return AjaxResult.success();
    }

    //TODO OAuth2.0

    /**
     * 登录
     *
     * @author Claire
     * @param loginParam 参数
     * @return Object
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginParam loginParam) {
        final AjaxResult res = memberFeignService.login(loginParam);
        final Integer code = res.getCode();

        if(code != HttpEnum.SUCCESS.getCode()) {
            return res;
        }

//        MemberVo data = res.getData(new TypeReference<MemberVo>() {});
        MemberVo data = (MemberVo) res.getData();
        Map<String, Object> response = new LinkedHashMap<>();
        final Long id = data.getId();
        if(id == null) {
            throw new LoginException(HttpEnum.LOGIN_ERROR.getCode(), HttpEnum.LOGIN_ERROR.getMsg());
        }

        final String mobile = data.getMobile();
        response.put("isBindMobile", (mobile == null || mobile.isEmpty()) ? false : true);

        final String token = RedisUtil.setToken(id);
        response.put("token", token);
        return AjaxResult.success(response);
    }
}
