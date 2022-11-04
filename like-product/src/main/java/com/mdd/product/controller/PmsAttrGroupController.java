package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsAttrGroupService;
import com.mdd.product.validate.PmsAttrGroupParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsAttrGroupListVo;
import com.mdd.product.vo.PmsAttrGroupDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 属性分组管理
 */
@RestController
@RequestMapping("api/group")
public class PmsAttrGroupController {

    @Resource
    IPmsAttrGroupService iPmsAttrGroupService;

    /**
     * 属性分组列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsAttrGroupListVo> list = iPmsAttrGroupService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 属性分组详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsAttrGroupDetailVo detail = iPmsAttrGroupService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 属性分组新增
     *
     * @param pmsAttrGroupParam 参数
     * @return Object
     */
    @Log(title = "属性分组新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsAttrGroupParam.create.class) @RequestBody PmsAttrGroupParam pmsAttrGroupParam) {
        iPmsAttrGroupService.add(pmsAttrGroupParam);
        return AjaxResult.success();
    }

    /**
     * 属性分组编辑
     *
     * @param pmsAttrGroupParam 参数
     * @return Object
     */
    @Log(title = "属性分组编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsAttrGroupParam.update.class) @RequestBody PmsAttrGroupParam pmsAttrGroupParam) {
        iPmsAttrGroupService.edit(pmsAttrGroupParam);
        return AjaxResult.success();
    }

    /**
     * 属性分组删除
     *
     * @param pmsAttrGroupParam 参数
     * @return Object
     */
    @Log(title = "属性分组删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsAttrGroupParam.delete.class) @RequestBody PmsAttrGroupParam pmsAttrGroupParam) {
        iPmsAttrGroupService.del(pmsAttrGroupParam.getAttrGroupId());
        return AjaxResult.success();
    }

}
