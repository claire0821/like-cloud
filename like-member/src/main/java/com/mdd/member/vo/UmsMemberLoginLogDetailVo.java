package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * UmsMemberLoginLogVo
 */
@Data
public class UmsMemberLoginLogDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // member_id
    private String ip;  // ip
    private String city;  // city
    private Integer loginType;  // 登录类型[1-web，2-app]

}