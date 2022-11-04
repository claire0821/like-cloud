package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IPmsAttrService;
import com.mdd.product.validate.PmsAttrParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsAttrListVo;
import com.mdd.product.vo.PmsAttrDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品属性管理
 */
@RestController
@RequestMapping("api/attr")
public class PmsAttrController {

    @Resource
    IPmsAttrService iPmsAttrService;

    /**
     * 商品属性列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsAttrListVo> list = iPmsAttrService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品属性详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsAttrDetailVo detail = iPmsAttrService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品属性新增
     *
     * @param pmsAttrParam 参数
     * @return Object
     */
    @Log(title = "商品属性新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsAttrParam.create.class) @RequestBody PmsAttrParam pmsAttrParam) {
        iPmsAttrService.add(pmsAttrParam);
        return AjaxResult.success();
    }

    /**
     * 商品属性编辑
     *
     * @param pmsAttrParam 参数
     * @return Object
     */
    @Log(title = "商品属性编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsAttrParam.update.class) @RequestBody PmsAttrParam pmsAttrParam) {
        iPmsAttrService.edit(pmsAttrParam);
        return AjaxResult.success();
    }

    /**
     * 商品属性删除
     *
     * @param pmsAttrParam 参数
     * @return Object
     */
    @Log(title = "商品属性删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsAttrParam.delete.class) @RequestBody PmsAttrParam pmsAttrParam) {
        iPmsAttrService.del(pmsAttrParam.getAttrId());
        return AjaxResult.success();
    }

}
