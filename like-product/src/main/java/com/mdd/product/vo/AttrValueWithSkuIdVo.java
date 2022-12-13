package com.mdd.product.vo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-05 11:37
 **/
@Data
public class AttrValueWithSkuIdVo {
    private String attrValue;
    private List<String> skuIds;
    private String strSkuIds;

    public void setStrSkuIds(String strSkuIds) {
        this.strSkuIds = strSkuIds;
        setSkuIds(Arrays.stream(strSkuIds.split(",")).collect(Collectors.toList()));
    }
}
