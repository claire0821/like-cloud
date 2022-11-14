package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.product.entity.Attr;
import com.mdd.product.entity.AttrGroup;
import com.mdd.product.service.IAttrAttrgroupRelationService;
import com.mdd.product.service.IAttrGroupService;
import com.mdd.product.service.IAttrService;
import com.mdd.product.service.ICategoryService;
import com.mdd.product.validate.AttrGroupParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.*;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 属性分组管理
 */
@RestController
@RequestMapping("api/product/attrgroup")
public class AttrGroupController {

    @Resource
    IAttrGroupService IAttrGroupService;
    @Resource
    ICategoryService iCategoryService;

    @Autowired
    IAttrService iAttrService;

    @Autowired
    IAttrAttrgroupRelationService iAttrAttrgroupRelationService;
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
        PageResult<AttrGroupListVo> list = IAttrGroupService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 属性分组详情
     *
     * @param attrGroupId 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("attrGroupId") Long attrGroupId) {
        PmsAttrGroupDetailVo detail = IAttrGroupService.detail(attrGroupId);
        return AjaxResult.success(detail);
    }

    /**
     * 属性分组新增
     *
     * @param attrGroupParam 参数
     * @return Object
     */
    @Log(title = "属性分组新增")
    @PostMapping("/add")
    public Object add(@Validated(value = AttrGroupParam.create.class) @RequestBody AttrGroupParam attrGroupParam) {
        IAttrGroupService.add(attrGroupParam);
        return AjaxResult.success();
    }

    /**
     * 属性分组编辑
     *
     * @param attrGroupParam 参数
     * @return Object
     */
    @Log(title = "属性分组编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = AttrGroupParam.update.class) @RequestBody AttrGroupParam attrGroupParam) {
        IAttrGroupService.edit(attrGroupParam);
        return AjaxResult.success();
    }

//    /**
//     * 属性分组删除
//     *
//     * @param pmsAttrGroupParam 参数
//     * @return Object
//     */
//    @Log(title = "属性分组删除")
//    @PostMapping("/del")
//    public Object del(@Validated(value = PmsAttrGroupParam.delete.class) @RequestBody PmsAttrGroupParam pmsAttrGroupParam) {
//        iPmsAttrGroupService.del(pmsAttrGroupParam.getAttrGroupId());
//        return AjaxResult.success();
//    }

    /**
     * 属性分组批量删除
     *
     * @param attrGroupIds 参数
     * @return Object
     */
    @Log(title = "属性分组批量删除")
    @PostMapping("/del")
    public Object del(@RequestBody Long[] attrGroupIds) {
        IAttrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return AjaxResult.success();
    }

    /**
     * 根据所属分类ID查找或模糊搜索
     */
    @Log(title = "属性分组批量删除")
    @GetMapping("/listByCatelogId")
    public Object listByCatelogId(@Validated PageParam pageParam,
                                  @RequestParam(value = "key",required = false) String key,
                                  @RequestParam(value = "catelogId",required = false) Long catelogId){
        PageResult<AttrGroupListVo> list = IAttrGroupService.list(pageParam, key,catelogId);
        return AjaxResult.success(list);
    }

    /**
     * 信息
     */
    @Log(title = "信息")
    @GetMapping("/info")
    public Object info(@RequestParam(value = "attrGroupId") Long attrGroupId){
        AttrGroup attrGroup = IAttrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        Long[] path = iCategoryService.findCatelogPath(catelogId);

        AttrGroupListVo vo = new AttrGroupListVo();
        BeanUtils.copyProperties(attrGroup, vo);
        vo.setCatelogPath(path);

        return AjaxResult.success(vo);
    }

    @Log(title = "移除关联")
    @PostMapping("/attr/relation/delete")
    public Object deleteRelation(@RequestBody AttrAttrgroupRelationListVo[] vos){
        iAttrService.deleteRelation(vos);
        return AjaxResult.success();
    }

    ///product/attrgroup/{attrgroupId}/attr/relation
    @Log(title = "根据分组id查找关联的所有基本属性")
    @GetMapping("/attr/relation")
    public Object attrRelation(@Validated PageParam pageParam,
                               @RequestParam Map<String, String> params){
        final Long attrgroupId = Long.valueOf(params.get("attrGroupId"));
        final PageResult<AttrListVo> listVoPageResult = iAttrService.getRelationAttr(pageParam, params,attrgroupId);
        return AjaxResult.success(listVoPageResult);
    }

    /**
     * 获取当前分组没有关联的所有属性
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/noattr/relation")
    public Object attrNoRelation(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        final Long attrgroupId = Long.valueOf(params.get("attrGroupId"));
        PageResult<AttrListVo> list = iAttrService.getNoRelationAttr(pageParam, params, attrgroupId);
        return AjaxResult.success(list);
    }


    /**
     * 新增关联
     * @param vos
     * @return
     */
    @PostMapping("/attr/addRelation")
    public Object addRelation(@RequestBody List<AttrAttrgroupRelationListVo> vos){
        iAttrAttrgroupRelationService.saveBatch(vos);
        return AjaxResult.success();
    }
}
//TODO 根据catelogId查询和其他条件查询
//TODO 点击三级分类才查询
//ToDO 代码生成层排序用数字输入框