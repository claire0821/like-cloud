package com.mdd.auth.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.validate.member.LoginParam;
import com.mdd.common.validate.member.RegParam;
import com.mdd.common.vo.MemberVo;
import org.springframework.cloud.openfeign.FeignClient;
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
    Object register(@RequestBody RegParam regParam);


    @PostMapping(value = "api/member/member/login")
    AjaxResult<MemberVo> login(@RequestBody LoginParam loginParam);
//
//    @PostMapping(value = "api/member/member/oauth2/login")
//    Object oauthLogin(@RequestBody SocialUser socialUser) throws Exception;
//
//    @PostMapping(value = "api/member/member/weixin/login")
//    Object weixinLogin(@RequestParam("accessTokenInfo") String accessTokenInfo);
}
