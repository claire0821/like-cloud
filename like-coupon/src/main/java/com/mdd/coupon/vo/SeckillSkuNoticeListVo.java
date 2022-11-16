package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * SeckillSkuNoticeVo
 */
@Data
public class SeckillSkuNoticeListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // member_id
    private Long skuId;  // sku_id
    private Long sessionId;  // 活动场次id
    private Date subcribeTime;  // 订阅时间
    private Date sendTime;  // 发送时间
    private Integer noticeType;  // 通知方式[0-短信，1-邮件]

}
