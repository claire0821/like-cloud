package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validate.PageParam;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.product.service.ISkuInfoService;
import com.mdd.product.validate.SkuInfoParam;
import com.mdd.product.vo.ProductDetaliVo;
import com.mdd.product.vo.SkuInfoDetailVo;
import com.mdd.product.vo.SkuInfoListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 商品
 */
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Resource
    ISkuInfoService iSkuInfoService;

    /**
     * 商品信息详情
     *
     * @param skuId 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@PathVariable @IDLongMust() @RequestParam("skuId") Long skuId) {
        ProductDetaliVo detail = iSkuInfoService.productDetail(skuId);
        return AjaxResult.success(detail);
    }

}
