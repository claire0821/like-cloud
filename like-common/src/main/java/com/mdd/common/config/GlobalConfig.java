package com.mdd.common.config;

/**
 * 全局配置
 */
public class GlobalConfig {

    // 开启调试模式
    public static Boolean debug = true;

    // 获取地址开关
    public static Boolean isAddressEnabled = false;

    // 当前代码版本
    public static String version = "v1.3.3";

    // 系统加密字符
    public static String secret = "UVTIyzCy";

    // Mysql表前缀
    public static String tablePrefix = "la_";

    // Redis键前缀
    public static String redisPrefix = "Like:";

    // 登录缓存键
    public static final String TokenKey = "token:";

    // 令牌缓存键
    public static final String backstageTokenKey = "backstage:token:";

    // 购物车缓存键
    public static final String CartKey = "cart:";

    // 购物车缓存键
    public static final String OrderKey = "order:";

    // role缓存键
    public static final String RoleKey = "role";

    // 短信验证码
    public static String redisSmsCode  = "smsCode:";

    // 资源访问前缀
    public static String publicPrefix = "api/uploads";

    // 上传映射目录
//    public static String uploadDirectory = "/www/uploads/likeadmin-java/";
    public static String uploadDirectory = "Y:/uploads/";

    // 上传图片限制
    public static Integer uploadImageSize = 1024 * 1024 * 10;

    // 上传视频限制
    public static Integer uploadVideoSize = 1024 * 1024 * 30;

    // 上传图片扩展
    public static String[] uploadImageExt = new String[] {"png", "jpg", "jpeg", "gif", "ico", "bmp"};

    // 上传视频扩展
    public static String[] uploadVideoExt = new String[] {"mp4", "mp3", "avi", "flv", "rmvb", "mov"};

    // 超级管理员
    public static final String SuperAdministrator = "admin";
    /**
     * loginid构造拼接字符串
     */
    public static String LOGINID_JOIN_CODE = ":";

    // 免登录验证
    public static String[] notLoginUri = new String[]{
            "/api/index",
            "/api/config",
            "/api/policy",
            "/api/search",
            "/api/hotSearch",
            "/api/decorate",
            "/api/sms/send",
            "/api/upload/image",

            "/api/login/check",
            "/api/login/codeUrl",
            "/api/login/oaLogin",
            "/api/login/register",
            "/api/login/forgotPassword",

            "/api/article/category",
            "/api/article/detail",
            "/api/article/list",
            "/api/member/member/login",
            "/api/member/member/register",
            "/api/member/level/getLevel",
    };
}
