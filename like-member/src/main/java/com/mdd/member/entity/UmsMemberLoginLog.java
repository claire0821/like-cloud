package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员登录记录实体
 */
@Data
public class UmsMemberLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long memberId; // member_id
    private Date createTime; // 创建时间
    private String ip; // ip
    private String city; // city
    private Integer loginType; // 登录类型[1-web，2-app]

}