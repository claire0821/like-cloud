package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SmsSeckillPromotionVo
 */
@Data
public class SmsSeckillPromotionListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String title;  // 活动标题
    private String startTime; // 开始日期
    private String endTime; // 结束日期
    private Long status;  // 上下线状态
    private String createTime; // 创建时间
    private Long userId;  // 创建人

}
