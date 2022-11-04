package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsSkuSaleAttrValueService;
import com.mdd.product.validate.PmsSkuSaleAttrValueParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsSkuSaleAttrValueListVo;
import com.mdd.product.vo.PmsSkuSaleAttrValueDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * sku销售属性&值管理
 */
@RestController
@RequestMapping("api/skusaleattrvalue")
public class PmsSkuSaleAttrValueController {

    @Resource
    IPmsSkuSaleAttrValueService iPmsSkuSaleAttrValueService;

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
        PageResult<PmsSkuSaleAttrValueListVo> list = iPmsSkuSaleAttrValueService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * sku销售属性&值详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSkuSaleAttrValueDetailVo detail = iPmsSkuSaleAttrValueService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * sku销售属性&值新增
     *
     * @param pmsSkuSaleAttrValueParam 参数
     * @return Object
     */
    @Log(title = "sku销售属性&值新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSkuSaleAttrValueParam.create.class) @RequestBody PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam) {
        iPmsSkuSaleAttrValueService.add(pmsSkuSaleAttrValueParam);
        return AjaxResult.success();
    }

    /**
     * sku销售属性&值编辑
     *
     * @param pmsSkuSaleAttrValueParam 参数
     * @return Object
     */
    @Log(title = "sku销售属性&值编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSkuSaleAttrValueParam.update.class) @RequestBody PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam) {
        iPmsSkuSaleAttrValueService.edit(pmsSkuSaleAttrValueParam);
        return AjaxResult.success();
    }

    /**
     * sku销售属性&值删除
     *
     * @param pmsSkuSaleAttrValueParam 参数
     * @return Object
     */
    @Log(title = "sku销售属性&值删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSkuSaleAttrValueParam.delete.class) @RequestBody PmsSkuSaleAttrValueParam pmsSkuSaleAttrValueParam) {
        iPmsSkuSaleAttrValueService.del(pmsSkuSaleAttrValueParam.getId());
        return AjaxResult.success();
    }

}
