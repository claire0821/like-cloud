package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsAttrGroupVo
 */
@Data
public class PmsAttrGroupListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long attrGroupId;  // 分组id
    private String attrGroupName;  // 组名
    private Long sort;  // 排序
    private String descript;  // 描述
    private String icon;  // 组图标
    private Long catelogId;  // 所属分类id

}
