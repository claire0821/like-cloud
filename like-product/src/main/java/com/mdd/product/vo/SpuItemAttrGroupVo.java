package com.mdd.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-05 11:35
 **/
@Data
@ToString
public class SpuItemAttrGroupVo {

    private String groupName;

    private List<Attr> attrs;

}
