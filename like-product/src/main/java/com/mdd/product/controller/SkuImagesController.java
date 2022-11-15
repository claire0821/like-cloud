package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.ISkuImagesService;
import com.mdd.product.validate.SkuImagesParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.SkuImagesListVo;
import com.mdd.product.vo.SkuImagesDetailVo;
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
 * sku图片管理
 */
@RestController
@RequestMapping("api/product/images")
public class SkuImagesController {

    @Resource
    ISkuImagesService iSkuImagesService;

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
        PageResult<SkuImagesListVo> list = iSkuImagesService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * sku图片详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SkuImagesDetailVo detail = iSkuImagesService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * sku图片新增
     *
     * @param skuImagesParam 参数
     * @return Object
     */
    @Log(title = "sku图片新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SkuImagesParam.create.class) @RequestBody SkuImagesParam skuImagesParam) {
        iSkuImagesService.add(skuImagesParam);
        return AjaxResult.success();
    }

    /**
     * sku图片编辑
     *
     * @param skuImagesParam 参数
     * @return Object
     */
    @Log(title = "sku图片编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SkuImagesParam.update.class) @RequestBody SkuImagesParam skuImagesParam) {
        iSkuImagesService.edit(skuImagesParam);
        return AjaxResult.success();
    }


    /**
     * sku图片批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "sku图片批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSkuImagesService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
