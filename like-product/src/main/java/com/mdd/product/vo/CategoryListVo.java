package com.mdd.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PmsCategoryVo
 */
@Data
public class CategoryListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long catId;  // 分类id
    private String name;  // 分类名称
    private Long parentCid;  // 父分类id
    private Integer catLevel;  // 层级
    private Integer showStatus;  // 是否显示[0-不显示，1显示]
    private Integer sort;  // 排序
    private String icon;  // 图标地址
    private String productUnit;  // 计量单位
    private Integer productCount;  // 商品数量

    //子分类
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//不为空才返回
    private List<CategoryListVo> children;

}
