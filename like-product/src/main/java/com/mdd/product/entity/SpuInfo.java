package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息实体
 */
@Data
public class SpuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // 商品id
    private String spuName; // 商品名称
    private String spuDescription; // 商品描述
    private Long catalogId; // 所属分类id
    private Long brandId; // 品牌id
    private BigDecimal weight; //
    private Integer publishStatus; // 上架状态[0 - 下架，1 - 上架]
    private Date createTime; //
    private Date updateTime; // 

}