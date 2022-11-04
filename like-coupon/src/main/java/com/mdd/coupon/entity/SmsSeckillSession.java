package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀活动场次实体
 */
@Data
public class SmsSeckillSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 场次名称
    private Date startTime; // 每日开始时间
    private Date endTime; // 每日结束时间
    private Integer status; // 启用状态
    private Date createTime; // 创建时间

}