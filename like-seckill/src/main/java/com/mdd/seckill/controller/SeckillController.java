package com.mdd.seckill.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.seckill.service.ISeckillService;
import com.mdd.seckill.to.SeckillSkuRedisTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 16:13
 **/
@RestController
@RequestMapping("api/seckill")
public class SeckillController {
    @Autowired
    ISeckillService iSeckillService;


    /**
     * 当前时间可以参与秒杀的商品信息
     * @return
     */
    @GetMapping(value = "/getCurrentSeckillSkus")
    @ResponseBody
    public AjaxResult<List<SeckillSkuRedisTo>> getCurrentSeckillSkus() {
        //获取到当前可以参加秒杀商品的信息
        List<SeckillSkuRedisTo> vos = iSeckillService.getCurrentSeckillSkus();
        return AjaxResult.success(vos);
    }


    /**
     * 根据skuId查询商品是否参加秒杀活动
     * @param skuId
     * @return
     */
    @GetMapping(value = "/sku")
    @ResponseBody
    public AjaxResult<SeckillSkuRedisTo> getSkuSeckilInfo(@RequestParam("skuId") Long skuId) {
        SeckillSkuRedisTo to = iSeckillService.getSkuSeckilInfo(skuId);
        return AjaxResult.success(to);
    }


    /**
     * 商品进行秒杀(秒杀开始)
     * @param killId
     * @param key
     * @param num
     * @return
     */
    @GetMapping(value = "/kill")
    public AjaxResult seckill(@RequestParam("killId") String killId,
                          @RequestParam("key") String key,
                          @RequestParam("num") Integer num,
                          Model model) {

        String orderSn = null;
        try {
            //1、判断是否登录
            orderSn = iSeckillService.kill(killId,key,num);
            model.addAttribute("orderSn",orderSn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }
}
