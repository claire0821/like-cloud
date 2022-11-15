package com.mdd.product.feign;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("like-coupon")
public interface CouponFeignService {
}
