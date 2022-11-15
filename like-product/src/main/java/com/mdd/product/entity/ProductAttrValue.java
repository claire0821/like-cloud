package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * spu属性值实体
 */
@Data
public class ProductAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long spuId; // 商品id
    private Long attrId; // 属性id
    private String attrName; // 属性名
    private String attrValue; // 属性值
    private Integer attrSort; // 顺序
    private Integer quickShow; // 快速展示【是否展示在介绍上；0-否 1-是】

}