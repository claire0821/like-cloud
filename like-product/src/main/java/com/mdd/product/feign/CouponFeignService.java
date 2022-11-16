package com.mdd.product.feign;


import com.mdd.common.core.AjaxResult;
import com.mdd.common.to.SkuReductionTo;
import com.mdd.common.to.SpuBoundTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("like-coupon")
public interface CouponFeignService {
    @PostMapping("api/coupon/spubounds/save")
    Object saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    @PostMapping("api/coupon/skufullreduction/saveinfo")
    Object saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
