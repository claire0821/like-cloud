package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsSkuImagesVo
 */
@Data
public class PmsSkuImagesListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private String imgUrl;  // 图片地址
    private Long imgSort;  // 排序
    private Long defaultImg;  // 默认图[0 - 不是默认图，1 - 是默认图]

}
