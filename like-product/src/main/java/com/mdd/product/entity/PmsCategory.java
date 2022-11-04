package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    private Integer catLevel; // 层级
    private Integer showStatus; // 是否显示[0-不显示，1显示]
    private Integer sort; // 排序
    private String icon; // 图标地址
    private String productUnit; // 计量单位
    private Integer productCount; // 商品数量

    //子分类
    @TableField(exist = false)
    private List<PmsCategory> chilren;

}