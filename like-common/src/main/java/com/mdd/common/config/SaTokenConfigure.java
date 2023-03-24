//package com.mdd.common.config;
//
//import cn.dev33.satoken.context.SaHolder;
//import cn.dev33.satoken.filter.SaServletFilter;
//import cn.dev33.satoken.interceptor.SaInterceptor;
//import cn.dev33.satoken.util.SaResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @program: server
// * @description: Sa-Token 权限认证 配置类
// * @author: Claire
// * @create: 2023-03-22 13:30
// **/
//@ConditionalOnExpression("!'${spring.application.name}'.equals('like-gateway')")
//@Configuration
//public class SaTokenConfigure implements WebMvcConfigurer {
//    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
//        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/api/auth/login")
//                .excludePathPatterns("/api/common/index/config");
//    }
////    /**
////     * 校验是否从网关转发
////     */
////    @Bean
////    public SaServletFilter getSaServletFilter() {
////        return new SaServletFilter()
////                .addInclude("/**")
////                .addExclude("/favicon.ico")
////                .addExclude("/api/auth/login")
////                .setAuth(r -> {
////                    // 校验 Id-Token 身份凭证     —— 以下两句代码可简化为：SaIdUtil.checkCurrentRequestToken();
////                    String token = SaHolder.getRequest().getHeader(SaIdUtil.ID_TOKEN);
////                    SaIdUtil.checkToken(token);
//////                    SaSameUtil.checkCurrentRequestToken();
////                })
////                .setError(e -> {
////                    return SaResult.error(e.getMessage());
////                })
////                ;
////    }
//}
