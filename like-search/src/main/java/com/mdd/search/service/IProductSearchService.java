package com.mdd.search.service;

import com.mdd.search.vo.ProductRelatedInfo;
import com.mdd.search.vo.ProductResultVo;
import com.mdd.search.vo.ProductSearchVo;

import java.io.IOException;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-25 17:41
 **/
public interface IProductSearchService {
    /**
     * 获取筛选参数
     * @param productSearchVo
     * @return
     */
    ProductResultVo getProductRelated(ProductSearchVo productSearchVo) throws IOException;
}
