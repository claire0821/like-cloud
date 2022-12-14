package com.mdd.order.feign;

import com.mdd.common.vo.CartItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("like-cart")
public interface ICartFeignService {
    /**
     * 查询当前用户购物车选中的商品项
     * @return
     */
    @GetMapping(value = "/currentUserCartItems")
    List<CartItemVo> getCurrentCartItems();
}
