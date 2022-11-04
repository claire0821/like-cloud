package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsProductAttrValueService;
import com.mdd.product.validate.PmsProductAttrValueParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsProductAttrValueListVo;
import com.mdd.product.vo.PmsProductAttrValueDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * spu属性值管理
 */
@RestController
@RequestMapping("api/value")
public class PmsProductAttrValueController {

    @Resource
    IPmsProductAttrValueService iPmsProductAttrValueService;

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
        PageResult<PmsProductAttrValueListVo> list = iPmsProductAttrValueService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu属性值详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsProductAttrValueDetailVo detail = iPmsProductAttrValueService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu属性值新增
     *
     * @param pmsProductAttrValueParam 参数
     * @return Object
     */
    @Log(title = "spu属性值新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsProductAttrValueParam.create.class) @RequestBody PmsProductAttrValueParam pmsProductAttrValueParam) {
        iPmsProductAttrValueService.add(pmsProductAttrValueParam);
        return AjaxResult.success();
    }

    /**
     * spu属性值编辑
     *
     * @param pmsProductAttrValueParam 参数
     * @return Object
     */
    @Log(title = "spu属性值编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsProductAttrValueParam.update.class) @RequestBody PmsProductAttrValueParam pmsProductAttrValueParam) {
        iPmsProductAttrValueService.edit(pmsProductAttrValueParam);
        return AjaxResult.success();
    }

    /**
     * spu属性值删除
     *
     * @param pmsProductAttrValueParam 参数
     * @return Object
     */
    @Log(title = "spu属性值删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsProductAttrValueParam.delete.class) @RequestBody PmsProductAttrValueParam pmsProductAttrValueParam) {
        iPmsProductAttrValueService.del(pmsProductAttrValueParam.getId());
        return AjaxResult.success();
    }

}
