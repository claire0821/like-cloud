package com.mdd.member;

import java.util.LinkedHashMap;
import java.util.Map;

public class LikeMemberThreadLocal {

    /**
     * 构造方法
     */
    public LikeMemberThreadLocal() {}

    /**
     * 取得本地线程对象
     */
    private static final ThreadLocal<LinkedHashMap<String, Object>> MY_LOCAL = new ThreadLocal<>();

    /**
     * 写入本地线程
     */
    public static void put(String key, Object val) {
        LinkedHashMap<String, Object> map = MY_LOCAL.get();
        if (map == null) {
            map = new LinkedHashMap<>();
        }

        map.put(key, val);
        MY_LOCAL.set(map);
    }

    /**
     * 获取本地线程
     */
    public static Object get(String key) {
        Map<String, Object> map = MY_LOCAL.get();
        if (map == null) {
            return null;
        }
        return map.getOrDefault(key, "");
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        Object adminId = LikeMemberThreadLocal.get("userId");
        if (adminId == null || adminId.toString().equals("")) {
            return Long.valueOf(0);
        }
        return Long.parseLong(adminId.toString());
    }

    /**
     * 删除本地线程
     */
    public static void remove() {
        MY_LOCAL.remove();
    }

}
