package com.mdd.search.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-25 17:25
 **/
@Data
public class ProductSearchVo {
    private Integer pageNumber = 1;
    private Integer pageSize = 2;

    private String keyword;
    private String sort;//排序条件 综合排序 销量 价格
    private String order;//升降序
    private Long category;//分类 单选
    private List<Long> brands;//品牌 多选

    private Integer hasStock = 1;//是否有货 0无库存 1有库存
    private Integer minPrice;//最低价
    private Integer maxPrice;//最高价
    private List<ScreenOptions> attrs;
    //private Map<Long, List<String>> attrs; //属性
}
