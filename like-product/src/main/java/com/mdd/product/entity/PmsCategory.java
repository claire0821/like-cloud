package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品三级分类实体
 */
@Data
public class PmsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="cat_id", type= IdType.AUTO)
    private Long catId; // 分类id
    private String name; // 分类名称
    private Long parentCid; // 父分类id
    private Long catLevel; // 层级
    private Long showStatus; // 是否显示[0-不显示，1显示]
    private Long sort; // 排序
    private String icon; // 图标地址
    private String productUnit; // 计量单位
    private Long productCount; // 商品数量

}