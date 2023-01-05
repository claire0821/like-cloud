package com.mdd.common.enums;

public enum HttpEnum {

    SUCCESS(200, "成功"),
    FAILED(300, "失败"),
    PARAMS_VALID_ERROR(310, "参数校验错误"),
    PARAMS_TYPE_ERROR(311, "参数类型错误"),
    REQUEST_METHOD_ERROR(312, "请求方法错误"),
    ASSERT_ARGUMENT_ERROR(313, "断言参数错误"),
    ASSERT_MYBATIS_ERROR(314, "断言Mybatis错误"),

    LOGIN_ACCOUNT_ERROR(330, "登录账号或密码错误"),
    LOGIN_DISABLE_ERROR(331, "登录账号已被禁用了"),
    TOKEN_EMPTY(332, "token参数为空"),
    TOKEN_INVALID(333, "token参数无效"),
    LIMIT_ERROR(334, "访问过于频繁，请稍后再试"),
    SMAE_MOBILE(335, "存在相同的手机号"),
    SAME_USERNAME(336, "存在相同的用户名"),
    ACCOUNT_MOBILE_EMPTY(337, "请输入账号名或者手机号码"),
    LOGIN_ERROR(338, "登录失败"),

    NO_PERMISSION(403, "无相关权限"),
    REQUEST_404_ERROR(404, "请求接口不存在"),

    SYSTEM_ERROR(500, "系统错误"),

    ORDER_ERROR(600, "订单错误"),
    ORDER_NO_STOCK(601, "商品库存不足");
    /**
     * 构造方法
     */
    private final int code;
    private final String msg;
    HttpEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取状态码
     *
     * @author fzr
     * @return Long
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取提示
     *
     * @author fzr
     * @return String
     */
    public String getMsg() {
        return this.msg;
    }

}
