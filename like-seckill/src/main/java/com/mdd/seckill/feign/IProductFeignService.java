package com.mdd.seckill.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.ProductDetaliSkuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gulimall-product")
public interface IProductFeignService {

    @GetMapping(value = "/api/product/skuinfo/getDetial")
    public AjaxResult<ProductDetaliSkuVo> getDetial(@RequestParam("skuId") Long skuId);
}
