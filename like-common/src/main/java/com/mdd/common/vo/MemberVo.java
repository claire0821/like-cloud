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
    private Long levelId;  // 会员等级id
    private String username;  // 用户名
    private String nickname;  // 昵称
    private String mobile = "";  // 手机号码
    private String email = "";  // 邮箱
    private String avatar;  // 头像
    private Integer gender;  // 性别
    private Integer sourceType;  // 用户来源
    private Integer status;  // 启用状态

}
