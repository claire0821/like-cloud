package com.mdd.order;

import java.util.LinkedHashMap;
import java.util.Map;

public class LikeOrderThreadLocal {

    /**
     * 构造方法
     */
    public LikeOrderThreadLocal() {}

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
        Object adminId = LikeOrderThreadLocal.get("userId");
        if (adminId == null || adminId.toString().equals("")) {
            return Long.valueOf(0);
        }
        return Long.parseLong(adminId.toString());
    }

    /**
     * 获取用户ID
     */
    public static Integer getUserType() {
        Object adminId = LikeOrderThreadLocal.get("userType");
        if (adminId == null || adminId.toString().equals("")) {
            return Integer.valueOf(0);
        }
        return Integer.parseInt(adminId.toString());
    }

    /**
     * 删除本地线程
     */
    public static void remove() {
        MY_LOCAL.remove();
    }

}
