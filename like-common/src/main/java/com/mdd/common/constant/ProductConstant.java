package com.mdd.common.constant;

/**
 * @program: server
 * @description: 属性 销售属性没有分组
 * @author: Claire
 * @create: 2022-11-11 17:50
 **/
public class ProductConstant {
    public enum  AttrEnum{
        ATTR_TYPE_BASE(1,"基本属性"),ATTR_TYPE_SALE(0,"销售属性");
        private int code;
        private String msg;

        AttrEnum(int code,String msg){
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
