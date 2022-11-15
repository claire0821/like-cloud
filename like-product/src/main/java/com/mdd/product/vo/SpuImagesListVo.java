package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SpuImagesVo
 */
@Data
public class SpuImagesListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long spuId;  // spu_id
    private String imgName;  // 图片名
    private String imgUrl;  // 图片地址
    private Integer imgSort;  // 顺序
    private Integer defaultImg;  // 是否默认图

}
