package com.mdd.product.vo;

import lombok.Data;

/**
 * @program: server
 * @description: spu保存
 * @author: Claire
 * @create: 2022-11-15 15:21
 **/
@Data
public class BaseAttrs {
    private Long attrId;
    private String attrValues;
    private int showDesc;
}
