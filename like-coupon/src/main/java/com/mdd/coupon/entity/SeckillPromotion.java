package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * 秒杀活动实体
 */
@Data
public class SeckillPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String title; // 活动标题
    private Date startTime; // 开始日期
    private Date endTime; // 结束日期
    private Integer status; // 上下线状态
    private Date createTime; // 创建时间
    private Long userId; // 创建人

}