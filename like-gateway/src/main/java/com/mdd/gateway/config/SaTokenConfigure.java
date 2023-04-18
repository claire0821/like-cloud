package com.mdd.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @program: server
 * @description: [Sa-Token 权限认证] 配置类
 * @author: Claire
 * @create: 2023-03-17 11:55
 **/
@Configuration
public class SaTokenConfigure {
    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    RouterConfig routerConfig;

    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 白名单
                .addExclude(ignoreUrlsConfig.getUrls().toArray(new String[0]))
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    //System.out.println(SaHolder.getRequest().getRequestPath());

                    // 登录校验 -- 拦截所有路由
                    SaRouter.match("/**", r -> StpUtil.checkLogin());

                    // 权限认证 -- 不同模块, 校验不同权限
                    final Map<String, String> permission = routerConfig.getPermission();
                    for (String path : permission.keySet()) {
                        SaRouter.match(path, () -> StpUtil.checkPermission(permission.get(path)));
                    }

                    //SaRouter.match("/api/article/cate/list", r -> StpUtil.checkPermission("article:cate:list"));
                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> {
                    //错误返回
                    e.printStackTrace();
                    return SaResult.code(332);
                })
                ;
    }
}
