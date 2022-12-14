package com.mdd.cart.controller;

import com.mdd.cart.service.ICartService;
import com.mdd.common.vo.CartItemVo;
import com.mdd.cart.vo.CartVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-06 10:40
 **/
@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    ICartService iCartService;

    /**
     * 获取当前用户的购物车商品项
     * @return
     */
    @GetMapping(value = "/getCartItems")
    public Object getCartItems() {
        CartVo cartVo = iCartService.getUserCartItems();
        return AjaxResult.success(cartVo);
    }

    /**
     * 添加商品到购物车
     * attributes.addFlashAttribute():将数据放在session中，可以在页面中取出，但是只能取一次
     * attributes.addAttribute():将数据放在url后面
     * @return
     */
    @GetMapping(value = "/addCartItem")
    public Object addCartItem(@RequestParam("skuId") Long skuId,
                              @RequestParam("num") Integer num) throws ExecutionException, InterruptedException {

        iCartService.addToCart(skuId,num);
        return AjaxResult.success();
    }

    /**
     * 商品是否选中
     * @param skuId
     * @param selected
     * @return
     */
    @GetMapping(value = "/changeCartItemSelected")
    public Object changeCartItemSelected(@RequestParam(value = "skuId") Long skuId,
                            @RequestParam(value = "selected") Boolean selected) {
        iCartService.checkItem(skuId,selected);
        return AjaxResult.success();
    }

    /**
     * 商品是否选中
     * @return
     */
    @GetMapping(value = "/selectAllCartItem")
    public Object selectAllCartItem(@RequestParam(value = "selected") Boolean selected) {
        iCartService.selectAllCartItem(selected);
        return AjaxResult.success();
    }

    /**
     * 改变商品数量
     * @param skuId
     * @param num
     * @return
     */
    @GetMapping(value = "/countCartItem")
    public Object countCartItem(@RequestParam(value = "skuId") Long skuId,
                            @RequestParam(value = "num") Integer num) {
        iCartService.changeItemCount(skuId,num);
        return AjaxResult.success();
    }


    /**
     * 删除商品信息
     * @param skuId
     * @return
     */
    @GetMapping(value = "/deleteCartItem")
    public Object deleteCartItem(@RequestParam("skuId") Long skuId) {
        iCartService.deleteIdCartInfo(skuId);
        return AjaxResult.success();

    }

    /**
     * 清空购物车
     * @return
     */
    @GetMapping(value = "/clearCartItem")
    public Object clearCartItem() {
        iCartService.clearCartInfo();
        return AjaxResult.success();
    }

    /**
     * 获取当前用户的购物车商品项
     * @return
     */
    @GetMapping(value = "/currentUserCartItems")
    public AjaxResult getCurrentCartItems() {
        List<CartItemVo> cartItemVoList = iCartService.getCurrentCartItems();
        return AjaxResult.success(cartItemVoList);
    }
}
