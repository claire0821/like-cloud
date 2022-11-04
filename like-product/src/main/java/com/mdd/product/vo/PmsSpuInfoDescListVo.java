package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsSpuInfoDescVo
 */
@Data
public class PmsSpuInfoDescListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long spuId;  // 商品id
    private String decript;  // 商品介绍

}
