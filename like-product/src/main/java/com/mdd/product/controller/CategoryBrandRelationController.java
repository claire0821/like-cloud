package com.mdd.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.config.aop.Log;
import com.mdd.product.entity.Brand;
import com.mdd.product.entity.CategoryBrandRelation;
import com.mdd.product.service.ICategoryBrandRelationService;
import com.mdd.product.validate.CategoryBrandRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.BrandListVo;
import com.mdd.product.vo.CategoryBrandRelationListVo;
import com.mdd.product.vo.CategoryBrandRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 品牌分类关联管理
 */
@RestController
@RequestMapping("api/product/categorybrandrelation")
public class CategoryBrandRelationController {

    @Resource
    ICategoryBrandRelationService iCategoryBrandRelationService;

    /**
     * 获取当前品牌关联的所有分类列表
     *
     * @param brandId 品牌id
     * @return Object
     */
    @GetMapping("/catelog/list")
    public Object cateloglist(@RequestParam("brandId")Long brandId) {
        final List<CategoryBrandRelation> list = iCategoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelation>().eq("brand_id", brandId));
        return AjaxResult.success(list);
    }

    /**
     *  /product/categorybrandrelation/brands/list
     */
    @GetMapping("/brands/list")
    public Object relationBrandsList(@RequestParam(value = "catId",required = true)Long catId){
        List<Brand> vos = iCategoryBrandRelationService.getBrandsByCatId(catId);

        List<BrandListVo> collect = vos.stream().map(item -> {
            BrandListVo brandVo = new BrandListVo();
            brandVo.setBrandId(item.getBrandId());
            brandVo.setName(item.getName());
            return brandVo;
        }).collect(Collectors.toList());

        return AjaxResult.success(collect);

    }
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
        PageResult<CategoryBrandRelationListVo> list = iCategoryBrandRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 品牌分类关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated   @IDMust()  @RequestParam("id") Long id) {
        CategoryBrandRelationDetailVo detail = iCategoryBrandRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 品牌分类关联新增
     *
     * @param categoryBrandRelationParam 参数
     * @return Object
     */
    @Log(title = "品牌分类关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CategoryBrandRelationParam.create.class) @RequestBody CategoryBrandRelationParam categoryBrandRelationParam) {
        iCategoryBrandRelationService.add(categoryBrandRelationParam);
        return AjaxResult.success();
    }

    /**
     * 品牌分类关联编辑
     *
     * @param categoryBrandRelationParam 参数
     * @return Object
     */
    @Log(title = "品牌分类关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CategoryBrandRelationParam.update.class) @RequestBody CategoryBrandRelationParam categoryBrandRelationParam) {
        iCategoryBrandRelationService.edit(categoryBrandRelationParam);
        return AjaxResult.success();
    }


    /**
     * 品牌分类关联批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "品牌分类关联批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iCategoryBrandRelationService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    /**
     * 保存
     */
    @Log(title = "品牌分类关联批量删除")
    @PostMapping("/save")
    public Object save(@RequestBody CategoryBrandRelation categoryBrandRelation){
        iCategoryBrandRelationService.saveDetail(categoryBrandRelation);
        return AjaxResult.success();
    }
}
//TODO 代码生成搜索选项
