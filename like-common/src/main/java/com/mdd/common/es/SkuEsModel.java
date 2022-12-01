package com.mdd.common.es;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-17 16:15
 **/
@Data
public class SkuEsModel implements Serializable {

    private Long skuId;

    private Long spuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    private String brandImg;

    private List<Attrs> attrs;

    @Data
    public static class Attrs {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }

}
