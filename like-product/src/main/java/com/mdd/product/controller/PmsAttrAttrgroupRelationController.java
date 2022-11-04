package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.validate.PageParam;
import com.mdd.product.service.IPmsAttrAttrgroupRelationService;
import com.mdd.product.validate.PmsAttrAttrgroupRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsAttrAttrgroupRelationListVo;
import com.mdd.product.vo.PmsAttrAttrgroupRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 属性&属性分组关联管理
 */
@RestController
@RequestMapping("api/relation")
public class PmsAttrAttrgroupRelationController {

    @Resource
    IPmsAttrAttrgroupRelationService iPmsAttrAttrgroupRelationService;

    /**
     * 属性&属性分组关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsAttrAttrgroupRelationListVo> list = iPmsAttrAttrgroupRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 属性&属性分组关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsAttrAttrgroupRelationDetailVo detail = iPmsAttrAttrgroupRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 属性&属性分组关联新增
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     * @return Object
     */
    @Log(title = "属性&属性分组关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsAttrAttrgroupRelationParam.create.class) @RequestBody PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam) {
        iPmsAttrAttrgroupRelationService.add(pmsAttrAttrgroupRelationParam);
        return AjaxResult.success();
    }

    /**
     * 属性&属性分组关联编辑
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     * @return Object
     */
    @Log(title = "属性&属性分组关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsAttrAttrgroupRelationParam.update.class) @RequestBody PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam) {
        iPmsAttrAttrgroupRelationService.edit(pmsAttrAttrgroupRelationParam);
        return AjaxResult.success();
    }

    /**
     * 属性&属性分组关联删除
     *
     * @param pmsAttrAttrgroupRelationParam 参数
     * @return Object
     */
    @Log(title = "属性&属性分组关联删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsAttrAttrgroupRelationParam.delete.class) @RequestBody PmsAttrAttrgroupRelationParam pmsAttrAttrgroupRelationParam) {
        iPmsAttrAttrgroupRelationService.del(pmsAttrAttrgroupRelationParam.getId());
        return AjaxResult.success();
    }

}
