package com.mdd.seckill.to;

import com.mdd.common.vo.ProductDetaliSkuVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: server
 * @description: 给Redis中存放的skuInfo的信息
 * @author: Claire
 * @create: 2023-01-05 16:15
 **/
@Data
public class SeckillSkuRedisTo {

    /**
     * 活动id
     */
    private Long promotionId;
    /**
     * 活动场次id
     */
    private Long promotionSessionId;
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    /**
     * 秒杀总量
     */
    private Integer seckillCount;
    /**
     * 每人限购数量
     */
    private Integer seckillLimit;
    /**
     * 排序
     */
    private Integer seckillSort;

    //sku的详细信息
    private ProductDetaliSkuVo skuInfo;

    //当前商品秒杀的开始时间
    private Long startTime;

    //当前商品秒杀的结束时间
    private Long endTime;

    //当前商品秒杀的随机码
    private String randomCode;
}
