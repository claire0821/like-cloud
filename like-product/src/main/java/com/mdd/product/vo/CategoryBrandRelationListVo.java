package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsCategoryBrandRelationVo
 */
@Data
public class CategoryBrandRelationListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 
    private Long brandId;  // 品牌id
    private Long catelogId;  // 分类id
    private String brandName;  // 
    private String catelogName;  // 

}
