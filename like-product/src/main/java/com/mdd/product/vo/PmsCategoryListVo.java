package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsCategoryVo
 */
@Data
public class PmsCategoryListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long catId;  // 分类id
    private String name;  // 分类名称
    private Long parentCid;  // 父分类id
    private Long catLevel;  // 层级
    private Long showStatus;  // 是否显示[0-不显示，1显示]
    private Long sort;  // 排序
    private String icon;  // 图标地址
    private String productUnit;  // 计量单位
    private Long productCount;  // 商品数量

}
