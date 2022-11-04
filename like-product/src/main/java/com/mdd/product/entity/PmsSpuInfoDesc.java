package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * spu信息介绍实体
 */
@Data
public class PmsSpuInfoDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="spu_id", type= IdType.AUTO)
    private Long spuId; // 商品id
    private String decript; // 商品介绍

}