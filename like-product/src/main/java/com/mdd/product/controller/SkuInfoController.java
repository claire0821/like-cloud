package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.entity.SkuInfo;
import com.mdd.product.service.ISkuInfoService;
import com.mdd.product.validate.SkuInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.ProductDetaliSkuVo;
import com.mdd.product.vo.ProductDetaliVo;
import com.mdd.product.vo.SkuInfoListVo;
import com.mdd.product.vo.SkuInfoDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * sku信息管理
 */
@RestController
@RequestMapping("api/product/skuinfo")
public class SkuInfoController {
    @Resource
    ISkuInfoService iSkuInfoService;

    /**
     * sku信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SkuInfoListVo> list = iSkuInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * sku信息详情
     *
     * @param skuId 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("skuId") Long skuId) {
        SkuInfoDetailVo detail = iSkuInfoService.detail(skuId);
        return AjaxResult.success(detail);
    }

    /**
     * sku信息新增
     *
     * @param skuInfoParam 参数
     * @return Object
     */
    @Log(title = "sku信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SkuInfoParam.create.class) @RequestBody SkuInfoParam skuInfoParam) {
        iSkuInfoService.add(skuInfoParam);
        return AjaxResult.success();
    }

    /**
     * sku信息编辑
     *
     * @param skuInfoParam 参数
     * @return Object
     */
    @Log(title = "sku信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SkuInfoParam.update.class) @RequestBody SkuInfoParam skuInfoParam) {
        iSkuInfoService.edit(skuInfoParam);
        return AjaxResult.success();
    }


    /**
     * sku信息批量删除
     *
     * @param skuIds 参数
     * @return Object
     */
    @Log(title = "sku信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] skuIds) {
        iSkuInfoService.removeByIds(Arrays.asList(skuIds));
        return AjaxResult.success();
    }

    /**
     * sku信息详情包括销售属性
     *
     * @param skuId 参数
     * @return Object
     */
    @Log(title = "sku信息详情包括销售属性")
    @GetMapping("/getDetial")
    public AjaxResult<ProductDetaliSkuVo> getDetial(@RequestParam("skuId") Long skuId) {
       ProductDetaliSkuVo productDetaliSkuVo = iSkuInfoService.getDetial(skuId);
        return AjaxResult.success(productDetaliSkuVo);
    }

    /**
     * 根据skuId查询当前商品的价格
     * @param skuId
     * @return
     */
    @GetMapping(value = "/price")
    public AjaxResult<BigDecimal> getPrice(@RequestParam("skuId") Long skuId) {
        //获取当前商品的信息
        SkuInfo skuInfo = iSkuInfoService.getById(skuId);
        //获取商品的价格
        BigDecimal price = skuInfo.getPrice();
        return AjaxResult.success(price);
    }
}
