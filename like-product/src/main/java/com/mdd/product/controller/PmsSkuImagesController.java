package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IPmsSkuImagesService;
import com.mdd.product.validate.PmsSkuImagesParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsSkuImagesListVo;
import com.mdd.product.vo.PmsSkuImagesDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * sku图片管理
 */
@RestController
@RequestMapping("api/images")
public class PmsSkuImagesController {

    @Resource
    IPmsSkuImagesService iPmsSkuImagesService;

    /**
     * sku图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsSkuImagesListVo> list = iPmsSkuImagesService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * sku图片详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSkuImagesDetailVo detail = iPmsSkuImagesService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * sku图片新增
     *
     * @param pmsSkuImagesParam 参数
     * @return Object
     */
    @Log(title = "sku图片新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSkuImagesParam.create.class) @RequestBody PmsSkuImagesParam pmsSkuImagesParam) {
        iPmsSkuImagesService.add(pmsSkuImagesParam);
        return AjaxResult.success();
    }

    /**
     * sku图片编辑
     *
     * @param pmsSkuImagesParam 参数
     * @return Object
     */
    @Log(title = "sku图片编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSkuImagesParam.update.class) @RequestBody PmsSkuImagesParam pmsSkuImagesParam) {
        iPmsSkuImagesService.edit(pmsSkuImagesParam);
        return AjaxResult.success();
    }

    /**
     * sku图片删除
     *
     * @param pmsSkuImagesParam 参数
     * @return Object
     */
    @Log(title = "sku图片删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSkuImagesParam.delete.class) @RequestBody PmsSkuImagesParam pmsSkuImagesParam) {
        iPmsSkuImagesService.del(pmsSkuImagesParam.getId());
        return AjaxResult.success();
    }

}
