package com.mdd.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("like-ware")
public interface WareFeignService {

    @PostMapping(value = "/ware/waresku/hasStock")
    Object getSkuHasStock(@RequestBody List<Long> skuIds);
}
