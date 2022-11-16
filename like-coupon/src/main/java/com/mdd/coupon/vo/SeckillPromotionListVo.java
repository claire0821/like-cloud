package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * SeckillPromotionVo
 */
@Data
public class SeckillPromotionListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String title;  // 活动标题
    private String startTime; // 开始日期
    private String endTime; // 结束日期
    private Integer status;  // 上下线状态
    private String createTime; // 创建时间
    private Long userId;  // 创建人

}
