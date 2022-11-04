package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsBrandService;
import com.mdd.product.validate.PmsBrandParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsBrandListVo;
import com.mdd.product.vo.PmsBrandDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("api/brand")
public class PmsBrandController {

    @Resource
    IPmsBrandService iPmsBrandService;

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
        PageResult<PmsBrandListVo> list = iPmsBrandService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 品牌详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsBrandDetailVo detail = iPmsBrandService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 品牌新增
     *
     * @param pmsBrandParam 参数
     * @return Object
     */
    @Log(title = "品牌新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsBrandParam.create.class) @RequestBody PmsBrandParam pmsBrandParam) {
        iPmsBrandService.add(pmsBrandParam);
        return AjaxResult.success();
    }

    /**
     * 品牌编辑
     *
     * @param pmsBrandParam 参数
     * @return Object
     */
    @Log(title = "品牌编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsBrandParam.update.class) @RequestBody PmsBrandParam pmsBrandParam) {
        iPmsBrandService.edit(pmsBrandParam);
        return AjaxResult.success();
    }

    /**
     * 品牌删除
     *
     * @param pmsBrandParam 参数
     * @return Object
     */
    @Log(title = "品牌删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsBrandParam.delete.class) @RequestBody PmsBrandParam pmsBrandParam) {
        iPmsBrandService.del(pmsBrandParam.getBrandId());
        return AjaxResult.success();
    }

}
