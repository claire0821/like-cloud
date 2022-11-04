package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品通知订阅实体
 */
@Data
public class SmsSeckillSkuNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long memberId; // member_id
    private Long skuId; // sku_id
    private Long sessionId; // 活动场次id
    private Date subcribeTime; // 订阅时间
    private Date sendTime; // 发送时间
    private Integer noticeType; // 通知方式[0-短信，1-邮件]

}