package com.mdd.auth.controller;

import com.mdd.auth.limit.annotation.LimitPoint;
import com.mdd.common.config.aop.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-21 16:13
 **/
public class SliderImageController {
//    @Autowired
//    private VerificationService verificationService;
//
//    @LimitPoint(name = "slider_image", key = "verification")
//    @GetMapping("/{verificationEnums}")
//    @Log(title = "获取校验接口,一分钟同一个ip请求10次")
//    public Object getSliderImage(@RequestHeader String uuid, @PathVariable VerificationEnums verificationEnums) {
//        return ResultUtil.data(verificationService.createVerification(verificationEnums, uuid));
//
//    }
//
//    @LimitPoint(name = "slider_image", key = "verification_pre_check", limit = 600)
//    @PostMapping("/{verificationEnums}")
//    @Log(title = "验证码预校验")
//    public Object verificationImage(Integer xPos, @RequestHeader String uuid, @PathVariable VerificationEnums verificationEnums) {
//        return ResultUtil.data(verificationService.preCheck(xPos, uuid, verificationEnums));
//    }
}
