package com.mdd.seckill.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.ProductDetaliSpuVo;
import com.mdd.common.vo.SeckillSessionWithSkusVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 16:17
 **/
@FeignClient("gulimall-coupon")
public interface ICouponFeignService {

    /**
     * 查询最近三天需要参加秒杀商品的信息
     * @return
     */
    @GetMapping(value = "/api/coupon/session/lates3DaySession")
    public AjaxResult<List<SeckillSessionWithSkusVo>> getLates3DaySession();
}
