package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.validate.BaseParam;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.product.entity.Brand;
import com.mdd.product.service.IBrandService;
import com.mdd.product.validate.BrandParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.BrandListVo;
import com.mdd.product.vo.BrandDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("api/product/brand")
public class BrandController {

    @Resource
    IBrandService iBrandService;

    /**
     * 品牌列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<BrandListVo> list = iBrandService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 品牌详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("brandId") Long brandId) {
        BrandDetailVo detail = iBrandService.detail(brandId);
        return AjaxResult.success(detail);
    }

    /**
     * 品牌新增
     *
     * @param brandParam 参数
     * @return Object
     */
    @Log(title = "品牌新增")
    @PostMapping("/add")
    public Object add(@Validated(value = BrandParam.create.class) @RequestBody BrandParam brandParam) {
        iBrandService.add(brandParam);
        return AjaxResult.success();
    }

    /**
     * 品牌编辑
     *
     * @param brandParam 参数
     * @return Object
     */
    @Log(title = "品牌编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = BrandParam.update.class) @RequestBody BrandParam brandParam) {
        iBrandService.edit(brandParam);
        return AjaxResult.success();
    }

    /**
     * 品牌删除
     *
     * @param brandParam 参数
     * @return Object
     */
    @Log(title = "品牌删除")
    @PostMapping("/del")
    public Object del(@Validated(value = BrandParam.delete.class) @RequestBody BrandParam brandParam) {
        iBrandService.del(brandParam.getBrandId());
        return AjaxResult.success();
    }

    /**
     * 更新显示状态
     * @param brands
     * @return
     */
    @Log(title = "更新显示状态")
    @PostMapping("/update/status")
    public Object updateStatus(@Validated(value = BaseParam.change.class) @RequestBody Brand brands) {
        iBrandService.updateBatchById(Arrays.asList(brands));
        return AjaxResult.success();
    }
}
//TODO 前端+后端校验
//TODO 增加模糊搜索
//TODO 品牌管理批量删除