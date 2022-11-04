package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * 商品阶梯价格实体
 */
@Data
public class SmsSkuLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long skuId; // spu_id
    private Long fullCount; // 满几件
    private BigDecimal discount; // 打几折
    private BigDecimal price; // 折后价
    private Integer addOther; // 是否叠加其他优惠[0-不可叠加，1-可叠加]

}