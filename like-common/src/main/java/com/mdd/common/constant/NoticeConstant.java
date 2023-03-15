package com.mdd.common.constant;

/**
 * @program: server
 * @description: 消息通知
 * @author: Claire
 * @create: 2023-03-10 09:30
 **/
public class NoticeConstant {
    //通知接收对象类型
    public enum  RecipientTypeEnum{
        MEMBER(1,"会员"),
        BUSINESS(2,"商家"),
        TERRACE(3,"平台"),
        VISITOR(4,"游客");
        private int code;
        private String msg;

        RecipientTypeEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static String getRecipientTypeEnumMsg(Integer code) {
        final RecipientTypeEnum[] values = RecipientTypeEnum.values();
        for (RecipientTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }

    //通知发送类型
    public enum  SendTypeEnum{
        SYSTEM_NOTIFICATION(1,"系统通知"),
        REVENUE_NOTICE(2,"收益通知");
        private int code;
        private String msg;

        SendTypeEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static String getSendTypeEnumMsg(Integer code) {
        final SendTypeEnum[] values = SendTypeEnum.values();
        for (SendTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }
}
