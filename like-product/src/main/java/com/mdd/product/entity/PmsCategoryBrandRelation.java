package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌分类关联实体
 */
@Data
public class PmsCategoryBrandRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // 
    private Long brandId; // 品牌id
    private Long catelogId; // 分类id
    private String brandName; // 
    private String catelogName; // 

}