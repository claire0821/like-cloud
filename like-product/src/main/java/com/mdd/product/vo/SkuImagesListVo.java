package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SkuImagesVo
 */
@Data
public class SkuImagesListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private String imgUrl;  // 图片地址
    private Integer imgSort;  // 排序
    private Integer defaultImg;  // 默认图[0 - 不是默认图，1 - 是默认图]

}
