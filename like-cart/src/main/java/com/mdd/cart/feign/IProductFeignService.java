package com.mdd.cart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("like-product")
public interface IProductFeignService {

    /**
     * 根据skuId查询sku信息
     * @param skuId
     * @return
     */
    @RequestMapping("api/product/skuinfo/getDetial")
    Object getDetial(@RequestParam("skuId") Long skuId);

    /**
     * 根据skuId查询当前商品的最新价格
     * @param skuId
     * @return
     */
    @GetMapping(value = "api/product/skuinfo/price")
    Object getPrice(@RequestParam("skuId") Long skuId);
}
