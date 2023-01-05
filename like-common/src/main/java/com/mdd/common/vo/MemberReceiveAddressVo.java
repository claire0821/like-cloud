package com.mdd.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * MemberReceiveAddressVo
 */
@Data
public class MemberReceiveAddressVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // member_id
    private String name;  // 收货人姓名
    private String phone;  // 电话
    private String postCode;  // 邮政编码
    private String province;  // 省份/直辖市
    private String city;  // 城市
    private String region;  // 区
    private String detailAddress;  // 详细地址(街道)
    private String areacode;  // 省市区代码
    private String provinceCityRegion;  // 省市区
    private Integer defaultStatus;  // 是否默认

    public String getProvinceCityRegion() {
        return this.province + " " + this.city + " " + this.region;
    }
}
