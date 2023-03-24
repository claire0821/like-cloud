package com.mdd.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program: server
 * @description: 权限
 * @author: Claire
 * @create: 2023-03-24 11:11
 **/
@Component
@ConfigurationProperties(prefix="router")
public class RouterConfig {
    private Map<String,String> permission;

    public Map<String, String> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, String> permission) {
        this.permission = permission;
    }
}
