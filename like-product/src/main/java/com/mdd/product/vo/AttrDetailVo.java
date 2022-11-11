package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * AttrVo
 */
@Data
public class AttrDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long attrId;  // 属性id
    private String attrName;  // 属性名
    private Integer searchType;  // 是否需要检索[0-不需要，1-需要]
    private String icon;  // 属性图标
    private String valueSelect;  // 可选值列表[用逗号分隔]
    private Integer attrType;  // 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
    private Long enable;  // 启用状态[0 - 禁用，1 - 启用]
    private Long catelogId;  // 所属分类
    private Integer showDesc;  // 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整

    /**
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;
    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    private Integer valueType;
    private Long attrGroupId;
}
