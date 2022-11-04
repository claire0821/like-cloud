package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsBrandVo
 */
@Data
public class PmsBrandDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long brandId;  // 品牌id
    private String name;  // 品牌名
    private String logo;  // 品牌logo地址
    private String descript;  // 介绍
    private Long showStatus;  // 显示状态[0-不显示；1-显示]
    private String firstLetter;  // 检索首字母
    private Long sort;  // 排序

}
