package com.mdd.cart;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.constant.MemberConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 拦截器
 */
@Component
public class LikeCartInterceptor implements HandlerInterceptor {

//    @Autowired
//    IMemberService iMemberService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 404拦截
        response.setContentType("application/json;charset=utf-8");
        if (response.getStatus() == 404) {
            AjaxResult result = AjaxResult.failed(HttpEnum.REQUEST_404_ERROR.getCode(), HttpEnum.REQUEST_404_ERROR.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 判断请求接口
        if (!(handler instanceof HandlerMethod)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 免登录接口
        String token = request.getHeader("token");
        token = RedisUtil.getToken(token);
        List<String> notLoginUri = Arrays.asList(GlobalConfig.notLoginUri);
        if (notLoginUri.contains(request.getRequestURI())) {
            if (StringUtil.isNotEmpty(token)) {
                Object uid = RedisUtil.get(token);
                if (uid != null) {
                    Integer userId = Integer.parseInt(uid.toString());
                    LikeCartThreadLocal.put("userId", userId);
                }
            }
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // Token是否为空
        if (StringUtils.isBlank(token)) {
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_EMPTY.getCode(), HttpEnum.TOKEN_EMPTY.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // Token是否过期
        if (!RedisUtil.existsToken(token)) {
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 用户信息缓存
        Object uid = RedisUtil.getUserID(token);
        Long userId = Long.parseLong(uid.toString());

        // 写入本地线程
        LikeCartThreadLocal.put("userId", userId);
//        LikeMemberThreadLocal.put("userSn", user.getSn());
//        LikeMemberThreadLocal.put("username", user.getUsername());
//        LikeMemberThreadLocal.put("nickname", user.getNickname());
//        LikeMemberThreadLocal.put("mobile", user.getMobile());

        // 验证通过继续操作
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
        LikeCartThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
