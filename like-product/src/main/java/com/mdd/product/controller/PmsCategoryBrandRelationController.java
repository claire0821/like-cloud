package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsCategoryBrandRelationService;
import com.mdd.product.validate.PmsCategoryBrandRelationParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsCategoryBrandRelationListVo;
import com.mdd.product.vo.PmsCategoryBrandRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 品牌分类关联管理
 */
@RestController
@RequestMapping("api/categorybrandrelation")
public class PmsCategoryBrandRelationController {

    @Resource
    IPmsCategoryBrandRelationService iPmsCategoryBrandRelationService;

    /**
     * 品牌分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsCategoryBrandRelationListVo> list = iPmsCategoryBrandRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 品牌分类关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsCategoryBrandRelationDetailVo detail = iPmsCategoryBrandRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 品牌分类关联新增
     *
     * @param pmsCategoryBrandRelationParam 参数
     * @return Object
     */
    @Log(title = "品牌分类关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsCategoryBrandRelationParam.create.class) @RequestBody PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam) {
        iPmsCategoryBrandRelationService.add(pmsCategoryBrandRelationParam);
        return AjaxResult.success();
    }

    /**
     * 品牌分类关联编辑
     *
     * @param pmsCategoryBrandRelationParam 参数
     * @return Object
     */
    @Log(title = "品牌分类关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsCategoryBrandRelationParam.update.class) @RequestBody PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam) {
        iPmsCategoryBrandRelationService.edit(pmsCategoryBrandRelationParam);
        return AjaxResult.success();
    }

    /**
     * 品牌分类关联删除
     *
     * @param pmsCategoryBrandRelationParam 参数
     * @return Object
     */
    @Log(title = "品牌分类关联删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsCategoryBrandRelationParam.delete.class) @RequestBody PmsCategoryBrandRelationParam pmsCategoryBrandRelationParam) {
        iPmsCategoryBrandRelationService.del(pmsCategoryBrandRelationParam.getId());
        return AjaxResult.success();
    }

}
