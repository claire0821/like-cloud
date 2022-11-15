package com.mdd.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description: spu保存
 * @author: Claire
 * @create: 2022-11-15 15:06
 **/
@Data
public class SpuSaveVo {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;
}
