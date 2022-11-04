package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsSpuImagesVo
 */
@Data
public class PmsSpuImagesDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long spuId;  // spu_id
    private String imgName;  // 图片名
    private String imgUrl;  // 图片地址
    private Long imgSort;  // 顺序
    private Long defaultImg;  // 是否默认图

}
