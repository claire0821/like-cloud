package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * AttrAttrgroupRelationVo
 */
@Data
public class AttrAttrgroupRelationListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long attrId;  // 属性id
    private Long attrGroupId;  // 属性分组id
    private Integer attrSort;  // 属性组内排序

}
