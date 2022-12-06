package com.mdd.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-05 11:34
 **/
@Data
@ToString
public class SkuItemSaleAttrVo {

    private Long attrId;

    private String attrName;

    private List<AttrValueWithSkuIdVo> attrValues;

}
