package com.mdd.common.constant;

/**
 * @program: server
 * @description: 会员账号状态
 * @author: Claire
 * @create: 2022-11-22 14:54
 **/
public class MemberConstant {
    public enum  MemberStateEnum{
        MEMBER_STATE_TYPE_ENABLE(1,"启用"),MEMBER_STATE_TYPE_DISABLE(0,"禁用");
        private int code;
        private String msg;

        MemberStateEnum(int code,String msg){
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
