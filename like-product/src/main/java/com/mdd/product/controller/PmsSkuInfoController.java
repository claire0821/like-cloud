package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IPmsSkuInfoService;
import com.mdd.product.validate.PmsSkuInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsSkuInfoListVo;
import com.mdd.product.vo.PmsSkuInfoDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * sku信息管理
 */
@RestController
@RequestMapping("api/skuinfo")
public class PmsSkuInfoController {

    @Resource
    IPmsSkuInfoService iPmsSkuInfoService;

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
        PageResult<PmsSkuInfoListVo> list = iPmsSkuInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * sku信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSkuInfoDetailVo detail = iPmsSkuInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * sku信息新增
     *
     * @param pmsSkuInfoParam 参数
     * @return Object
     */
    @Log(title = "sku信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSkuInfoParam.create.class) @RequestBody PmsSkuInfoParam pmsSkuInfoParam) {
        iPmsSkuInfoService.add(pmsSkuInfoParam);
        return AjaxResult.success();
    }

    /**
     * sku信息编辑
     *
     * @param pmsSkuInfoParam 参数
     * @return Object
     */
    @Log(title = "sku信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSkuInfoParam.update.class) @RequestBody PmsSkuInfoParam pmsSkuInfoParam) {
        iPmsSkuInfoService.edit(pmsSkuInfoParam);
        return AjaxResult.success();
    }

    /**
     * sku信息删除
     *
     * @param pmsSkuInfoParam 参数
     * @return Object
     */
    @Log(title = "sku信息删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSkuInfoParam.delete.class) @RequestBody PmsSkuInfoParam pmsSkuInfoParam) {
        iPmsSkuInfoService.del(pmsSkuInfoParam.getSkuId());
        return AjaxResult.success();
    }

}
