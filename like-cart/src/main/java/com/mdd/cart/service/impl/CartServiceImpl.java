package com.mdd.cart.service.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.mdd.cart.LikeCartThreadLocal;
import com.mdd.cart.feign.IProductFeignService;
import com.mdd.cart.service.ICartService;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.CartItemVo;
import com.mdd.cart.vo.CartVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.constant.CartConstant;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.vo.ProductDetaliSkuVo;
import com.mdd.common.vo.ProductDetaliSpuVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;


/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-06-30 17:06
 **/

@Slf4j
@Service("cartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private IProductFeignService iProductFeignService;

    @Autowired
    Executor executor;

    @Override
    public void addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        //用户id
        final Long userId = LikeCartThreadLocal.getUserId();
        CartItemVo cartItemVo = (CartItemVo) RedisUtil.hGet(GlobalConfig.CartKey + userId, String.valueOf(skuId));

        //如果没有就添加数据
        if(!RedisUtil.hExists(GlobalConfig.CartKey + userId, String.valueOf(skuId))) {
            cartItemVo = new CartItemVo();
            //开启第一个异步任务
            CartItemVo finalCartItemVo = cartItemVo;
            CompletableFuture<Void> getSkuInfoFuture = CompletableFuture.runAsync(() -> {
                //1、远程查询当前要添加商品的信息
                try{
                    Map<String, Object> skuInfoMap = null;
                    final AjaxResult<ProductDetaliSkuVo> result = iProductFeignService.getDetial(skuId);
                    if(result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                        ProductDetaliSkuVo productDetaliSkuVo = result.getData();
                        finalCartItemVo.setSkuQuantity(num);
                        finalCartItemVo.setSkuId(productDetaliSkuVo.getSkuId());
                        finalCartItemVo.setSkuName(productDetaliSkuVo.getTitle());
                        finalCartItemVo.setSkuPic(productDetaliSkuVo.getImg());
                        finalCartItemVo.setSkuPrice(productDetaliSkuVo.getPrice());
                        finalCartItemVo.setSaleValueStr(productDetaliSkuVo.getSaleValueStr());
                        finalCartItemVo.setStatus(CartConstant.CartItemStatusEnum.CART_ITEM_STATUS_ENUM_NORMAL.getCode());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }, executor);

            CompletableFuture<Void> getSpuInfoFuture = CompletableFuture.runAsync(() -> {
                //1、远程查询当前要添加商品spu的信息
                try{
                    final AjaxResult<ProductDetaliSpuVo> result = iProductFeignService.getDetialBySkuId(skuId);
                    if(result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                        ProductDetaliSpuVo productDetaliSpuVo = result.getData();
                        finalCartItemVo.setSpuId(productDetaliSpuVo.getSpuId());
                        finalCartItemVo.setSpuName(productDetaliSpuVo.getSpuName());
                        finalCartItemVo.setBrandId(productDetaliSpuVo.getBrandId());
                        finalCartItemVo.setBrandName(productDetaliSpuVo.getBrandName());
                        //finalCartItemVo.setSpuPic();
                        finalCartItemVo.setCategoryId(productDetaliSpuVo.getCatelogId());
                        finalCartItemVo.setCategoryName(productDetaliSpuVo.getCatelogName());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }, executor);


            //等待所有的异步任务全部完成
            CompletableFuture.allOf(getSkuInfoFuture,getSpuInfoFuture).get();

            //TODO 会员价
            finalCartItemVo.setShowMemberPrice(false);
            finalCartItemVo.setMemberPrice(finalCartItemVo.getSkuPrice());

            RedisUtil.hSet(GlobalConfig.CartKey + userId, String.valueOf(skuId),finalCartItemVo);

        } else {  //购物车有此商品，修改数量即可
            cartItemVo.setSkuQuantity(cartItemVo.getSkuQuantity() + num);
            RedisUtil.hSet(GlobalConfig.CartKey + userId, String.valueOf(skuId),cartItemVo);
        }

    }

    @Override
    public void clearCartInfo() {
        final Long userId = LikeCartThreadLocal.getUserId();
        RedisUtil.del(GlobalConfig.CartKey + userId);
    }

    @Override
    public void checkItem(Long skuId, Boolean selected) {
        final Long userId = LikeCartThreadLocal.getUserId();
        //查询购物车里面的商品
        CartItemVo cartItemVo = (CartItemVo) RedisUtil.hGet(GlobalConfig.CartKey + userId, String.valueOf(skuId));
        //修改商品状态
        cartItemVo.setSelected(selected);
        RedisUtil.hSet(GlobalConfig.CartKey + userId, String.valueOf(skuId),cartItemVo);
    }

    /**
     * 修改购物项数量
     * @param skuId
     * @param num
     */
    @Override
    public void changeItemCount(Long skuId, Integer num) {
        final Long userId = LikeCartThreadLocal.getUserId();
        //查询购物车里面的商品
        CartItemVo cartItemVo = (CartItemVo) RedisUtil.hGet(GlobalConfig.CartKey + userId, String.valueOf(skuId));
        //修改商品数量
        cartItemVo.setSkuQuantity(num);
        RedisUtil.hSet(GlobalConfig.CartKey + userId, String.valueOf(skuId),cartItemVo);
    }


    /**
     * 删除购物项
     * @param skuId
     */
    @Override
    public void deleteIdCartInfo(Long skuId) {
        final Long userId = LikeCartThreadLocal.getUserId();
        RedisUtil.hDel(GlobalConfig.CartKey + userId, String.valueOf(skuId));
    }

    @Override
    public void selectAllCartItem(boolean selected) {
        final Long userId = LikeCartThreadLocal.getUserId();
        //查询购物车里面的商品
        final Map<Object, Object> objectObjectMap = RedisUtil.hmGet(GlobalConfig.CartKey + userId);
        //修改商品状态
        objectObjectMap.forEach((k,v) -> {
            CartItemVo cartItemVo = (CartItemVo) v;
            if (cartItemVo.getStatus().equals(CartConstant.CartItemStatusEnum.CART_ITEM_STATUS_ENUM_NORMAL.getCode()) && cartItemVo.getSelected() != selected) {
                cartItemVo.setSelected(selected);
                RedisUtil.hSet(GlobalConfig.CartKey + userId, String.valueOf(k),cartItemVo);
            }
        });
    }

    @Override
    public List<CartItemVo> getCurrentCartItems() {
        List<CartItemVo> cartItemVoList = new ArrayList<>();
        //获取当前用户登录的信息
        final Long userId = LikeCartThreadLocal.getUserId();
        //如果用户未登录直接返回null
        if (userId == null) {
            return null;
        } else {
            //获取购物车项
            final Map<Object, Object> objectObjectMap = RedisUtil.hmGet(GlobalConfig.CartKey + userId);
            if(objectObjectMap == null || objectObjectMap.size() == 0) {
                return null;
            }
            final Collection<Object> values = objectObjectMap.values();
            List<CartItemVo> list = new ArrayList<CartItemVo>();
            for (Object value : values) {
                if(value instanceof CartItemVo) {
                    CartItemVo cartItemVo = (CartItemVo) value;
                    //更新价格
                    if(cartItemVo.getSelected() && cartItemVo.getStatus().equals(CartConstant.CartItemStatusEnum.CART_ITEM_STATUS_ENUM_NORMAL.getCode())) {
                        AjaxResult<BigDecimal> result = iProductFeignService.getPrice(cartItemVo.getSkuId());
                        if(result.getData().equals(HttpEnum.SUCCESS)) {
                            cartItemVo.setSkuPrice(result.getData());
                        }
                        cartItemVoList.add(cartItemVo);
                    }

                }
            }
            return cartItemVoList;
        }
    }

    @Override
    public CartVo getUserCartItems() {

        CartVo cartVo = new CartVo();
        //获取当前用户登录的信息
        final Long userId = LikeCartThreadLocal.getUserId();
        //如果用户未登录直接返回null
        if (userId == null) {
            return null;
        } else {
            //获取购物车项
            final Map<Object, Object> objectObjectMap = RedisUtil.hmGet(GlobalConfig.CartKey + userId);
            if(objectObjectMap == null || objectObjectMap.size() == 0) {
                return null;
            }
            final Collection<Object> values = objectObjectMap.values();
            List<CartItemVo> list = new ArrayList<CartItemVo>();
            for (Object value : values) {
                if(value instanceof CartItemVo) {
                    CartItemVo cartItemVo = (CartItemVo) value;
                    //更新价格
                    if(cartItemVo.getSelected() && cartItemVo.getStatus().equals(CartConstant.CartItemStatusEnum.CART_ITEM_STATUS_ENUM_NORMAL.getCode())) {
                        AjaxResult<BigDecimal> result = iProductFeignService.getPrice(cartItemVo.getSkuId());
                        if(result.getCode().equals(HttpEnum.SUCCESS)) {
                            cartItemVo.setSkuPrice(result.getData());
                            cartItemVo.setShowMemberPrice(true);
                            cartItemVo.setMemberPrice(BigDecimal.valueOf(1000));
                        }

                        //TODO 会员价
                        cartItemVo.setShowMemberPrice(false);
                        //TODO 优惠信息
                        //TODO 积分信息

                    }
                    list.add(cartItemVo);
                }
            }
            cartVo.setItems(list);
            return cartVo;
        }
    }
}
