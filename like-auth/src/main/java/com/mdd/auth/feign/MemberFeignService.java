package com.mdd.auth.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.validate.user.RegParam;
import com.mdd.common.vo.MemberVo;
import com.mdd.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-21 17:53
 **/
@FeignClient("like-member")
public interface MemberFeignService {

    @PostMapping(value = "api/member/member/register")
    AjaxResult<Object> register(@RequestBody RegParam regParam);


    @PostMapping(value = "api/member/member/login")
    AjaxResult<UserVo> login(@Validated() @RequestBody LoginParam loginParam);
//
//    @PostMapping(value = "api/member/member/oauth2/login")
//    Object oauthLogin(@RequestBody SocialUser socialUser) throws Exception;
//
//    @PostMapping(value = "api/member/member/weixin/login")
//    Object weixinLogin(@RequestParam("accessTokenInfo") String accessTokenInfo);
}
