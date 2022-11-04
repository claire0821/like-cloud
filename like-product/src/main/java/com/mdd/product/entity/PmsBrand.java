package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌实体
 */
@Data
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="brand_id", type= IdType.AUTO)
    private Long brandId; // 品牌id
    private String name; // 品牌名
    private String logo; // 品牌logo地址
    private String descript; // 介绍
    private Long showStatus; // 显示状态[0-不显示；1-显示]
    private String firstLetter; // 检索首字母
    private Long sort; // 排序

}