package com.mdd.product.feign;

import com.mdd.common.es.SkuEsModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("like-search")
public interface SearchFeignService {
    @PostMapping(value = "api/search/save/product")
    public Object productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
