package com.mdd.common.constant;

/**
 * @program: server
 * @description: 属性 销售属性没有分组
 * @author: Claire
 * @create: 2022-11-11 17:50
 **/
public class CartConstant {
    public enum  CartItemStatusEnum{
        CART_ITEM_STATUS_ENUM_NORMAL(1,"正常"),CART_ITEM_STATUS_ENUM_EXPIRED(0,"已失效");
        private int code;
        private String msg;

        CartItemStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
