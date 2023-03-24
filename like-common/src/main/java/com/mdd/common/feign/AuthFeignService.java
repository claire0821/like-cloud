package com.mdd.common.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("like-auth")
public interface AuthFeignService {
    /**
     * 获取登录用户信息
     *
     * @author Claire
     * @return AjaxResult
     */
    @PostMapping("api/auth/getUserInfo")
    AjaxResult<UserVo> getUserInfo();
}
