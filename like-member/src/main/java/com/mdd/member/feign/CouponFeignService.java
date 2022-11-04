package com.mdd.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 14:19
 **/
@FeignClient("like-coupon")
public interface CouponFeignService {
    @RequestMapping("/api/smscoupon/member/list")
    public Object membercoupons();
}
