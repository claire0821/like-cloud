package com.mdd.ware.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.OrderVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface IOrderFeignService {
    @GetMapping(value = "/api/order/status")
    AjaxResult<OrderVo> getOrderStatus(@RequestParam("orderSn") String orderSn);
}
