package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员收货地址实体
 */
@Data
public class MemberReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long memberId; // member_id
    private String name; // 收货人姓名
    private String phone; // 电话
    private String postCode; // 邮政编码
    private String province; // 省份/直辖市
    private String city; // 城市
    private String region; // 区
    private String detailAddress; // 详细地址(街道)
    private String areacode; // 省市区代码
    private Integer defaultStatus; // 是否默认

}