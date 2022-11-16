package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * 首页轮播广告实体
 */
@Data
public class HomeAdv implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 名字
    private String pic; // 图片地址
    private Date startTime; // 开始时间
    private Date endTime; // 结束时间
    private Integer status; // 状态
    private Integer clickCount; // 点击数
    private String url; // 广告详情连接地址
    private String note; // 备注
    private Integer sort; // 排序
    private Long publisherId; // 发布者
    private Long authId; // 审核者

}