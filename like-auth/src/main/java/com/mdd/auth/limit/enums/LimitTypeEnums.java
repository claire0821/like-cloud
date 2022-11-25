package com.mdd.auth.limit.enums;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-21 16:22
 **/
public enum LimitTypeEnums {
    /**
     * 自定义key(即全局限流)
     */
    CUSTOMER,
    /**
     * 根据请求者IP（IP限流）
     */
    IP
}
