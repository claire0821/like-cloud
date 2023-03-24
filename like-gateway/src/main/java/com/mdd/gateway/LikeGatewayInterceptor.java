//package com.mdd.gateway;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.mdd.admin.config.AdminConfig;
//import com.mdd.admin.service.system.ISystemAuthAdminService;
//import com.mdd.admin.service.system.ISystemAuthPermService;
//import com.mdd.common.constant.OrderConstant;
//import com.mdd.common.core.AjaxResult;
//import com.mdd.common.enums.HttpEnum;
//import com.mdd.common.utils.RedisUtil;
//import com.mdd.common.utils.ToolsUtil;
//import com.mdd.common.vo.UserVo;
//import com.mdd.gateway.config.IgnoreUrlsConfig;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
///**
// * 拦截器
// */
//@Component
//public class LikeGatewayInterceptor implements HandlerInterceptor {
//    @Autowired
//    IgnoreUrlsConfig ignoreUrlsConfig;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 404拦截
//        response.setContentType("application/json;charset=utf-8");
//        if (response.getStatus() == 404) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.REQUEST_404_ERROR.getCode(), HttpEnum.REQUEST_404_ERROR.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        // 判断请求接口
//        if (!(handler instanceof HandlerMethod)) {
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // 路由转权限
//        String prefix = "/api/";
//        String route = request.getRequestURI().replaceFirst(prefix, "");
//        String auths = route.replace("/", ":");
//
//        // 免登录接口
//        List<String> notLoginUri = ignoreUrlsConfig.getUrls();
//        if (notLoginUri.contains(auths)) {
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // Token是否为空
//        String token = request.getHeader("token");
//        if (StringUtils.isBlank(token)) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_EMPTY.getCode(), HttpEnum.TOKEN_EMPTY.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        // Token是否过期
//        if (!RedisUtil.existsToken(token)) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        UserVo user = RedisUtil.getUser(token);
//
//        // 令牌剩余30分钟自动续签
//        RedisUtil.renewalToken(token);
//
//
//        // 免权限验证接口
//        List<String> notAuthUri = Arrays.asList(AdminConfig.notAuthUri);
//        if (notAuthUri.contains(auths)) {
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // 验证通过继续操作
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        LikeAdminThreadLocal.remove();
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
