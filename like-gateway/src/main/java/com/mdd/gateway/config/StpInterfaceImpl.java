package com.mdd.gateway.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.mdd.gateway.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: server
 * @description: 自定义权限验证接口扩展
 * @author: Claire
 * @create: 2023-03-22 15:51
 **/

@Component
public class StpInterfaceImpl implements StpInterface {
    /**
     * 返回一个账号所拥有的权限码集合 redis取不到值
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        final List<String> roleList = StpUtil.getRoleList();
        if(roleList == null || roleList.size() <= 0) return null;
        final List<String> rolePermission = RedisUtil.getRolePermission(roleList);
        return rolePermission;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String str = (String) loginId;
        final String[] split = str.split(":");
        if(split.length >= 3) {
            String roles = split[2];
            final String[] split1 = roles.split(",");
            return Arrays.asList(split1);
        }
        if(str.contains("管理员:1")) {
            List<String> list = new ArrayList();
            list.add("admin");
            return list;
        }
        return null;
    }

}