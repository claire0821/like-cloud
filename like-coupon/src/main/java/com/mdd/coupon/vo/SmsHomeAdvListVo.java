package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SmsHomeAdvVo
 */
@Data
public class SmsHomeAdvListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 名字
    private String pic;  // 图片地址
    private String startTime; // 开始时间
    private String endTime; // 结束时间
    private Integer status;  // 状态
    private Long clickCount;  // 点击数
    private String url;  // 广告详情连接地址
    private String note;  // 备注
    private Long sort;  // 排序
    private Long publisherId;  // 发布者
    private Long authId;  // 审核者

}
