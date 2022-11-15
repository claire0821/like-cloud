package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.ISkuSaleAttrValueService;
import com.mdd.product.validate.SkuSaleAttrValueParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.SkuSaleAttrValueListVo;
import com.mdd.product.vo.SkuSaleAttrValueDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * sku销售属性&值管理
 */
@RestController
@RequestMapping("api/product/skusaleattrvalue")
public class SkuSaleAttrValueController {

    @Resource
    ISkuSaleAttrValueService iSkuSaleAttrValueService;

    /**
     * sku销售属性&值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SkuSaleAttrValueListVo> list = iSkuSaleAttrValueService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * sku销售属性&值详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SkuSaleAttrValueDetailVo detail = iSkuSaleAttrValueService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * sku销售属性&值新增
     *
     * @param skuSaleAttrValueParam 参数
     * @return Object
     */
    @Log(title = "sku销售属性&值新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SkuSaleAttrValueParam.create.class) @RequestBody SkuSaleAttrValueParam skuSaleAttrValueParam) {
        iSkuSaleAttrValueService.add(skuSaleAttrValueParam);
        return AjaxResult.success();
    }

    /**
     * sku销售属性&值编辑
     *
     * @param skuSaleAttrValueParam 参数
     * @return Object
     */
    @Log(title = "sku销售属性&值编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SkuSaleAttrValueParam.update.class) @RequestBody SkuSaleAttrValueParam skuSaleAttrValueParam) {
        iSkuSaleAttrValueService.edit(skuSaleAttrValueParam);
        return AjaxResult.success();
    }


    /**
     * sku销售属性&值批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "sku销售属性&值批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSkuSaleAttrValueService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
