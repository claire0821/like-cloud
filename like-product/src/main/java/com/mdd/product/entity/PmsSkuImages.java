package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * sku图片实体
 */
@Data
public class PmsSkuImages implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long skuId; // sku_id
    private String imgUrl; // 图片地址
    private Long imgSort; // 排序
    private Long defaultImg; // 默认图[0 - 不是默认图，1 - 是默认图]

}