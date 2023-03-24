package com.mdd.common.constant;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-03-22 10:15
 **/
public class LoginConstant {
    public enum  DeviceTypeEnum{
        PC(1,"pc"),APP(2,"app"),XCX(3,"xcx");
        private int code;
        private String msg;

        DeviceTypeEnum(int code,String msg){
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
