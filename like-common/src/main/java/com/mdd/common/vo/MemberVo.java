package com.mdd.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MemberVo
 */
@Data
public class MemberVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 会员id
    private String sn; // 会员码
    private Long levelId;  // 会员等级id
    private String levelName;  // 会员等级
    private String username;  // 用户名
    private String nickname;  // 昵称
    private String mobile = "";  // 手机号码
    private String email = "";  // 邮箱
    private String avatar;  // 头像
    private Integer gender;  // 性别
    private Date birth;  // 生日
    private String city;  // 所在城市
    private String job;  // 职业
    private String sign;  // 个性签名
    private Integer sourceType;  // 用户来源
    private Integer integration;  // 积分
    private Integer growth;  // 成长值
    private String nextLevelTips;//距离升级还差
    private Integer noticeNum; //未读消息
    private Integer money; //余额
    private Integer coupon; //优惠券数量
    private Integer waitPay;//待付款
    private Integer waitDelivery;//待发货
    private Integer waitTake;//待收货
    private Integer waitComment;//商品评价
    private Integer afterSale;//退款/售后
    private Integer status;  // 启用状态

}
