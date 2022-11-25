package com.mdd.auth.controller;

import com.mdd.common.config.aop.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * @program: server
 * @description: 会员登录注册
 * @author: Claire
 * @create: 2022-11-21 15:38
 **/
public class MemberController {

//    @Log(title = "商品属性编辑")
//    @PostMapping("/smsLogin")
//    public Object smsLogin(@NotNull(message = "手机号为空") @RequestParam String mobile,
//                                          @NotNull(message = "验证码为空") @RequestParam String code,
//                                          @RequestHeader String uuid) {
//        if (smsUtil.verifyCode(mobile, VerificationEnums.LOGIN, uuid, code)) {
//            return ResultUtil.data(memberService.mobilePhoneLogin(mobile));
//        } else {
//            throw new ServiceException(ResultCode.VERIFICATION_SMS_CHECKED_ERROR);
//        }
//    }
}
