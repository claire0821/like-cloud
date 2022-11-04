package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * UmsMemberVo
 */
@Data
public class UmsMemberDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long levelId;  // 会员等级id
    private String username;  // 用户名
    private String password;  // 密码
    private String nickname;  // 昵称
    private String mobile;  // 手机号码
    private String email;  // 邮箱
    private String header;  // 头像
    private Long gender;  // 性别
    private Date birth;  // 生日
    private String city;  // 所在城市
    private String job;  // 职业
    private String sign;  // 个性签名
    private Long sourceType;  // 用户来源
    private Long integration;  // 积分
    private Long growth;  // 成长值
    private Long status;  // 启用状态

}
