package com.mdd.product.vo;

import com.mdd.product.entity.Attr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description: spu保存
 * @author: Claire
 * @create: 2022-11-15 15:21
 **/
@Data
public class Skus {
    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> descar;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}