package com.mdd.common.constant;

/**
 * @program: server
 * @description: 订单状态枚举
 * @author: Claire
 * @create: 2022-12-14 16:24
 **/
public class OrderConstant {
    public enum OrderStatusEnum {
        CREATE_NEW(0,"待付款"),
        PAYED(1,"已付款"),
        SENDED(2,"已发货"),
        RECIEVED(3,"已完成"),
        CANCLED(4,"已关闭"),
        SERVICING(5,"售后中"),
        SERVICED(6,"售后完成");
        private Integer code;
        private String msg;

        OrderStatusEnum(Integer code, String msg) {
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

    public enum SourceTypeEnum {
        PC(0,"PC端"),
        APP(1,"移动端"),
        APPLET(2,"小程序端");
        private Integer code;
        private String msg;

        SourceTypeEnum(Integer code, String msg) {
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

    public enum TypeEnum {
        ORDINARY(0,"普通订单"),
        SECKILL(1,"秒杀订单"),
        COLLAGE(2,"拼团订单"),
        BARGAIN(3,"砍价订单");
        private Integer code;
        private String msg;

        TypeEnum(Integer code, String msg) {
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

    public enum DeliveryTypeEnum {
        EXPRESS_DELIVERY(0,"快递发货"),
        SELF_CONLLECTION(1,"门店自提");
        private Integer code;
        private String msg;

        DeliveryTypeEnum(Integer code, String msg) {
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

    public enum OperateManTypeEnum {
        USER(1,"用户"),
        SYSTEM(2,"系统"),
        ADMINISTRATORS(3,"管理员"),
        BUSINESS(4,"商家");
        private Integer code;
        private String msg;

        OperateManTypeEnum(Integer code, String msg) {
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

    public enum ActionTypeEnum {
        PLACE_ORDER(1,"提交订单"),
        PAY_ORDER(2,"支付订单"),
        ORDER_OVERDUE_UNPAID_CANCELLATION(3,"订单超时未付款取消"),
        CLOSE_ORDER(4,"关闭订单"),
        COMPLETE_ORDER(5,"完成订单"),
        MERCHANT_SHIPMENT(6,"商家发货"),
        CONFIRM_RECEIPT(7,"确认收货"),
        APPLY_FOR_AFTER_SALES_SERVICE(8,"申请售后"),
        REFUND_SUCCEEDED(9,"退款成功"),
        AFTER_SALES_COMPLETION(10,"售后完成");


        private Integer code;
        private String msg;

        ActionTypeEnum(Integer code, String msg) {
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

    public static String getOrderStatusEnumMsg(Integer code) {
        final OrderStatusEnum[] values = OrderStatusEnum.values();
        for (OrderStatusEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }

    public static String getSourceTypeEnumMsg(Integer code) {
        final SourceTypeEnum[] values = SourceTypeEnum.values();
        for (SourceTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }

    public static String getTypeEnumMsg(Integer code) {
        final TypeEnum[] values = TypeEnum.values();
        for (TypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }

    public static String getDeliveryTypeEnumMsg(Integer code) {
        final DeliveryTypeEnum[] values = DeliveryTypeEnum.values();
        for (DeliveryTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }

    public static String getOperateManTypeEnumMsg(Integer code) {
        final OperateManTypeEnum[] values = OperateManTypeEnum.values();
        for (OperateManTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }

    public static String getActionTypeEnumMsg(Integer code) {
        final ActionTypeEnum[] values = ActionTypeEnum.values();
        for (ActionTypeEnum value : values) {
            if(value.getCode().equals(code)) {
                return value.getMsg();
            }
        }
        return "错误";
    }
}
