package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * MemberVo
 */
@Data
public class MemberDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long levelId;  // 会员等级id
    private String username;  // 用户名
//    private String password;  // 密码
    private String nickname;  // 昵称
    private String mobile;  // 手机号码
    private String email;  // 邮箱
    private String avatar;  // 头像
    private Integer gender;  // 性别
    private Date birth;  // 生日
    private String city;  // 所在城市
    private String job;  // 职业
    private String sign;  // 个性签名
    private Integer sourceType;  // 用户来源
    private Integer integration;  // 积分
    private Integer growth;  // 成长值
    private Integer status;  // 启用状态

}
