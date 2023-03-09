package com.mdd.common.constant;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 15:49
 **/
public class PayConstant {
    public enum PayEnum{
        PAY_TYPE_ZHIFUBAO(1,"支付宝支付"),
        PAY_TYPE_WEIXIN(2,"微信支付"),
        PAY_TYPE_BALANCE(3,"余额支付"),
        PAY_TYPE_OFFLINE(4,"线下支付");
        private Integer code;
        private String msg;

        PayEnum(int code,String msg){
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

    public static String getPayEnumMsg(Integer code) {
        final PayEnum[] values = PayEnum.values();
        for (PayEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "未支付";
    }
}
