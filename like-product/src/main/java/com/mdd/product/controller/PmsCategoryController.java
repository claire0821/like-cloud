package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.validate.BaseParam;
import com.mdd.product.entity.PmsCategory;
import com.mdd.product.service.IPmsCategoryService;
import com.mdd.product.validate.PmsCategoryParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsCategoryListVo;
import com.mdd.product.vo.PmsCategoryDetailVo;
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
 * 商品三级分类管理
 */
@RestController
@RequestMapping("api/product/category")
public class PmsCategoryController {

    @Resource
    IPmsCategoryService iPmsCategoryService;

    /**
     * 商品三级分类列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsCategoryListVo> list = iPmsCategoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 商品三级分类列表 查出所以分类以及子分类，以树形结构组装
     *
     * @return Object
     */
    @GetMapping("/list/tree")
    public Object list() {
        //查出所有分类
        final List<PmsCategory> pmsCategories = iPmsCategoryService.listWithTree();
        //组装父子结构
        final List<PmsCategory> level1Menu = pmsCategories.stream().filter(category ->
            category.getParentCid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu,pmsCategories));
            return menu;
        }).sorted((menu1,menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return AjaxResult.success(level1Menu);
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root
     * @param all
     * @return
     */
    private List<PmsCategory> getChildrens(PmsCategory root, List<PmsCategory> all) {
        final List<PmsCategory> collect = all.stream().filter(category -> {
            return category.getParentCid().equals(root.getCatId());
        }).map(category -> {
            //找子菜单
            category.setChildren(getChildrens(category,all));
            return category;
        }).sorted((menu1, menu2) -> {
            //菜单排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 商品三级分类详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsCategoryDetailVo detail = iPmsCategoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品三级分类新增
     *
     * @param pmsCategoryParam 参数
     * @return Object
     */
    @Log(title = "商品三级分类新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsCategoryParam.create.class) @RequestBody PmsCategoryParam pmsCategoryParam) {
        iPmsCategoryService.add(pmsCategoryParam);
        return AjaxResult.success();
    }

    /**
     * 商品三级分类编辑
     *
     * @param pmsCategoryParam 参数
     * @return Object
     */
    @Log(title = "商品三级分类编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsCategoryParam.update.class) @RequestBody PmsCategoryParam pmsCategoryParam) {
        iPmsCategoryService.edit(pmsCategoryParam);
        return AjaxResult.success();
    }

    /**
     * 商品三级分类删除
     *
     * @param cateIds 参数
     * @return Object
     */
    @Log(title = "商品三级分类删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsCategoryParam.delete.class) @RequestBody Long[] cateIds) {
//        iPmsCategoryService.del(pmsCategoryParam.getCatId());
        iPmsCategoryService.removeMenuByIds(Arrays.asList(cateIds));
        return AjaxResult.success();
    }

    @Log(title = "更新节点")
    @PostMapping("/update/sort")
    public Object updateSort(@RequestBody PmsCategory[] categorys) {
        iPmsCategoryService.updateBatchById(Arrays.asList(categorys));
        return AjaxResult.success();
    }

}
