package com.mdd.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-25 17:29
 **/
@Data
public class ProductRelatedInfo {
    /**
     * 品牌集合
     */
    List<ScreenOptions> brands;
    /**
     * 分类集合
     */
    List<ScreenOptions> categories;
    /**
     * 其他参数集合
     */
    List<ScreenOptions> paramOptions;
}
