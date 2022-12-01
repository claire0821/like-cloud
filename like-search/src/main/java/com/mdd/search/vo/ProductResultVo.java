package com.mdd.search.vo;

import com.mdd.common.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * @program: server
 * @description: es查询得到的集合包含商品和筛选参数
 * @author: Claire
 * @create: 2022-11-29 17:56
 **/
@Data
public class ProductResultVo {
    /**
     * 查询到的所有商品信息
     */
    private List<SkuEsModel> products;
    /**
     * 当前页码
     */
    private Integer pageNumber;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页码
     */
    private Integer totalPages;

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
    List<ScreenOptions> attrs;

    //TODO 高亮显示
}
