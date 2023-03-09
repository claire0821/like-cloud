package com.mdd.common.constant;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-10 15:03
 **/
public class CouponConstan {
    public enum BoundsWorkEnum{
        BOUNDS_WORK_ENUM_1(1,"无优惠，不赠送成长积分"),
        BOUNDS_WORK_ENUM_2(2,"无优惠，赠送成长积分"),
        BOUNDS_WORK_ENUM_3(3,"无优惠，不赠送购物积分"),
        BOUNDS_WORK_ENUM_4(4,"无优惠，赠送购物积分"),
        BOUNDS_WORK_ENUM_5(5,"无优惠，不赠送成长积分，不赠送购物积分"),
        BOUNDS_WORK_ENUM_6(6,"无优惠，赠送成长积分，赠送购物积分");
        private int code;
        private String msg;

        BoundsWorkEnum(int code,String msg){
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

    public enum GetTypeEnum{
        BACKGROUND_GIFT(1,"后台赠送"),
        ACTIVE_CLAIM(2,"主动领取"),
        EVENT_GIFT(3,"活动赠送");
        private int code;
        private String msg;

        GetTypeEnum(int code,String msg){
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

    /**
     * 优惠券使用状态
     */
    public enum UseStatusEnum{
        NOT_USED(1,"未使用"),
        USED(2,"已使用"),
        EXPIRED(3,"已过期");
        private int code;
        private String msg;

        UseStatusEnum(int code,String msg){
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

    public enum GetCountTypeEnum{
        UNLIMITED(1,"不限制领取传次数"),
        LIMIT_TIMES(2,"限制次数"),
        LIMIT_TIMES_OF_EVERY_DAY(3,"每天限制数量");
        private int code;
        private String msg;

        GetCountTypeEnum(int code,String msg){
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

    /**
     * 发送总量类型
     */
    public enum PublishCountTypeEnum{
        UNLIMITED(1,"不限制"),
        LIMIT_TIMES(2,"限制张数");
        private int code;
        private String msg;

        PublishCountTypeEnum(int code,String msg){
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

    /**
     * 使用条件类型
     */
    public enum ConditionTypeEnum{
        NO_THRESHOLD(1,"无门槛"),
        ORDER_FULFILLMENT_AMOUNT(2,"订单满足金额");
        private int code;
        private String msg;

        ConditionTypeEnum(int code,String msg){
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

    /**
     * 用券时间类型
     */
    public enum UseTimeTypeEnum{
        FIXED_TIME(1,"固定时间"),
        FROM_THE_DAY_OF_RECEIPT(2,"领券当天起"),
        FROM_THE_NEXT_DAY_OF_RECEIPT(3,"领券次日起");
        private int code;
        private String msg;

        UseTimeTypeEnum(int code,String msg){
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

    /**
     * 优惠券状态
     */
    public enum SatusEnum{
        UNPUBLISHED(1,"未发布"),
        PUBLISHED(2,"已发布"),
        END(3,"结束");
        private int code;
        private String msg;

        SatusEnum(int code,String msg){
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
    /**
     * 适用商品类型
     */
    public enum UseTypeEnum{
        UNIVERSAL(1,"全场通用"),
        SPECIFY_CATEGORY(2,"指定分类"),
        SPECIFIED_PRODUCT(3,"指定商品"),
        SPECIFIED_PRODUCT_NOT_AVAILABLE(4,"指定商品不可用");
        private int code;
        private String msg;

        UseTypeEnum(int code,String msg){
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
