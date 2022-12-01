package com.mdd.search.service.impl;

import com.mdd.common.es.SkuEsModel;
import com.mdd.search.service.IProductSaveService;
import com.mdd.search.util.EsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @program: server
 * @description: 商品上架
 * @author: Claire
 * @create: 2022-11-29 11:10
 **/
@Service
public class IProductSaveServiceImpl implements IProductSaveService {
    @Autowired
    EsClient esClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        return esClient.bulkSave(skuEsModels);
    }
}
