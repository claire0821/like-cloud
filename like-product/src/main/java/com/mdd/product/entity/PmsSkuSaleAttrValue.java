package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * sku销售属性&值实体
 */
@Data
public class PmsSkuSaleAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long skuId; // sku_id
    private Long attrId; // attr_id
    private String attrName; // 销售属性名
    private String attrValue; // 销售属性值
    private Long attrSort; // 顺序

}