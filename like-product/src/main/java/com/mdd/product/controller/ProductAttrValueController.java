package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IProductAttrValueService;
import com.mdd.product.validate.ProductAttrValueParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.ProductAttrValueListVo;
import com.mdd.product.vo.ProductAttrValueDetailVo;
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
 * spu属性值管理
 */
@RestController
@RequestMapping("api/product/value")
public class ProductAttrValueController {

    @Resource
    IProductAttrValueService iProductAttrValueService;

    /**
     * spu属性值列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<ProductAttrValueListVo> list = iProductAttrValueService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu属性值详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        ProductAttrValueDetailVo detail = iProductAttrValueService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu属性值新增
     *
     * @param productAttrValueParam 参数
     * @return Object
     */
    @Log(title = "spu属性值新增")
    @PostMapping("/add")
    public Object add(@Validated(value = ProductAttrValueParam.create.class) @RequestBody ProductAttrValueParam productAttrValueParam) {
        iProductAttrValueService.add(productAttrValueParam);
        return AjaxResult.success();
    }

    /**
     * spu属性值编辑
     *
     * @param productAttrValueParam 参数
     * @return Object
     */
    @Log(title = "spu属性值编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = ProductAttrValueParam.update.class) @RequestBody ProductAttrValueParam productAttrValueParam) {
        iProductAttrValueService.edit(productAttrValueParam);
        return AjaxResult.success();
    }


    /**
     * spu属性值批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "spu属性值批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iProductAttrValueService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
