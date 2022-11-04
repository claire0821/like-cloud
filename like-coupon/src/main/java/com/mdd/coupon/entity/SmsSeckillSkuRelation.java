package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 秒杀活动商品关联实体
 */
@Data
public class SmsSeckillSkuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long promotionId; // 活动id
    private Long promotionSessionId; // 活动场次id
    private Long skuId; // 商品id
    private Long seckillPrice; // 秒杀价格
    private Long seckillCount; // 秒杀总量
    private Long seckillLimit; // 每人限购数量
    private Long seckillSort; // 排序

}