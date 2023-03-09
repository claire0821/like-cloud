package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * 会员实体
 */
@Data
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String sn; // 会员码
    private Long levelId; // 会员等级id
    private String username; // 用户名
    private String password; // 密码
    private String nickname; // 昵称
    private String mobile; // 手机号码
    private String email; // 邮箱
    private Integer gender; // 性别
    private Date birth; // 生日
    private String salt; // 加密盐巴
    private String avatar;//头像
    private String city; // 所在城市
    private String job; // 职业
    private String sign; // 个性签名
    private Integer sourceType; // 注册渠道: [1=微信小程序, 2=微信公众号, 3=手机H5, 4=电脑PC, 5=苹果APP, 6=安卓APP]
    private Integer integration; // 积分
    private Integer growth; // 成长值
    private Integer status; // 启用状态
    private Date createTime; // 注册时间
    private Date updateTime; // 更新时间

}