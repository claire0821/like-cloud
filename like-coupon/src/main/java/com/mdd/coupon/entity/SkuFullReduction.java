package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * 商品满减信息实体
 */
@Data
public class SkuFullReduction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long skuId; // spu_id
    private BigDecimal fullPrice; // 满多少
    private BigDecimal reducePrice; // 减多少
    private Integer addOther; // 是否参与其他优惠

}