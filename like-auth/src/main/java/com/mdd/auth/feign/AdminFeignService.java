package com.mdd.auth.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("like-admin")
public interface AdminFeignService {
    /**
     * 登录系统
     *
     * @author Claire
     * @param loginParam 登录参数
     * @return AjaxResult
     */
    @PostMapping("api/system/login")
    AjaxResult<UserVo> login(@Validated() @RequestBody LoginParam loginParam);
}
