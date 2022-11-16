package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * SeckillSessionVo
 */
@Data
public class SeckillSessionListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 场次名称
    private String startTime; // 每日开始时间
    private String endTime; // 每日结束时间
    private Integer status;  // 启用状态
    private String createTime; // 创建时间

}
