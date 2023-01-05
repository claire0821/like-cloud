package com.mdd.ware.feign;

import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-16 17:27
 **/
@FeignClient("like-product")
public interface IProductFeignService {

    @GetMapping("api/product/skuinfo/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("skuId") Long skuId);
}
