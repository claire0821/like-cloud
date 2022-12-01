package com.mdd.search.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetScriptContextResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.mdd.common.config.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.search.service.IProductSearchService;
import com.mdd.search.util.EsClient;
import com.mdd.search.vo.ProductRelatedInfo;
import com.mdd.search.vo.ProductResultVo;
import com.mdd.search.vo.ProductSearchVo;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-25 17:02
 **/
@RestController
@RequestMapping("api/search")
public class SearchController {
    @Autowired
    IProductSearchService iProductSearchService;

    @Autowired
    EsClient esClient;
    @PostConstruct
    public void init() {
        try {
            if(!esClient.isExistsIndex()) {
                esClient.createIndex();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Log(title = "从es中根据条件检索商品")
    @PostMapping("/product")
    public Object getProduct(@RequestBody ProductSearchVo productSearchVo) throws IOException {
        ProductResultVo productResultVo = iProductSearchService.getProductRelated(productSearchVo);
        return AjaxResult.success(productResultVo);
    }
}
