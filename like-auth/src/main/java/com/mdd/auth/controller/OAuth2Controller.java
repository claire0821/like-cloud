package com.mdd.auth.controller;

import com.mdd.auth.feign.MemberFeignService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: server
 * @description: 处理社交登录请求
 * @author: Claire
 * @create: 2022-11-22 11:40
 **/
public class OAuth2Controller {
    @Autowired
    MemberFeignService memberFeignService;


}
