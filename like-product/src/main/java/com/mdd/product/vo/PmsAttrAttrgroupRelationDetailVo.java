package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsAttrAttrgroupRelationVo
 */
@Data
public class PmsAttrAttrgroupRelationDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long attrId;  // 属性id
    private Long attrGroupId;  // 属性分组id
    private Long attrSort;  // 属性组内排序

}
