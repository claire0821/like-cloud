package com.mdd.search.service;

import com.mdd.common.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface IProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
