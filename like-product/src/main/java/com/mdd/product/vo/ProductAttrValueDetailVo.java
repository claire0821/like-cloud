package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * ProductAttrValueVo
 */
@Data
public class ProductAttrValueDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long spuId;  // 商品id
    private Long attrId;  // 属性id
    private String attrName;  // 属性名
    private String attrValue;  // 属性值
    private Integer attrSort;  // 顺序
    private Integer quickShow;  // 快速展示【是否展示在介绍上；0-否 1-是】

}
