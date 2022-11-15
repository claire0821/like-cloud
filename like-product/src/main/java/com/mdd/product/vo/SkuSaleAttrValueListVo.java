package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SkuSaleAttrValueVo
 */
@Data
public class SkuSaleAttrValueListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private Long attrId;  // attr_id
    private String attrName;  // 销售属性名
    private String attrValue;  // 销售属性值
    private Integer attrSort;  // 顺序

}
