package com.mdd.gateway.utils;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;
    private static final String redisPrefix = "Like:";
    // role缓存键
    public static final String RoleKey = "role";
    /**
     * 注入Redis
     *
     * @author fzr
     * @param redisTemplate Redis对象
     */
    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 对象句柄
     *
     * @author fzr
     * @return RedisTemplate
     */
    public static RedisTemplate<String, Object> handler() {
        return redisTemplate;
    }


    /**
     * 返回一个账号所拥有的权限码集合
     * @param roles
     * @return
     */
    public static List<String> getRolePermission(List<String> roles) {
        List<String> permission = new ArrayList<>();
        for (String role : roles) {
            final List<String> obj = (List<String>) redisTemplate.opsForHash().get(redisPrefix + RoleKey, role);
            permission.addAll(obj);
        }
        return permission;
    }
}
