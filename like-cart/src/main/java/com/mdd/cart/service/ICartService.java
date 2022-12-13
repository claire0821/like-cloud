package com.mdd.cart.service;

import com.mdd.cart.vo.CartItemVo;
import com.mdd.cart.vo.CartVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICartService {

    CartVo getUserCartItems();
    /**
     * 将商品添加至购物车
     * @param skuId
     * @param num
     * @return
     */
    void addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    /**
     * 清空购物车的数据
     */
    void clearCartInfo();

    /**
     * 勾选购物项
     * @param skuId
     * @param selected
     */
    void checkItem(Long skuId, Boolean selected);

    /**
     * 改变商品数量
     * @param skuId
     * @param num
     */
    void changeItemCount(Long skuId, Integer num);


    /**
     * 删除购物项
     * @param skuId
     */
    void deleteIdCartInfo(Long skuId);

    void selectAllCartItem(boolean selected);
}
