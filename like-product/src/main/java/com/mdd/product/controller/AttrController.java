package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.validate.BaseParam;
import com.mdd.product.entity.ProductAttrValue;
import com.mdd.product.service.IAttrService;
import com.mdd.product.service.IProductAttrValueService;
import com.mdd.product.validate.AttrParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.AttrListVo;
import com.mdd.product.vo.AttrDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 商品属性管理
 */
@RestController
@RequestMapping("api/product/attr")
public class AttrController {

    @Resource
    IAttrService iAttrService;
    @Autowired
    IProductAttrValueService iProductAttrValueService;

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
        PageResult<AttrListVo> list = iAttrService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品属性详情
     *
     * @param attrId 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust()  @RequestParam("attrId") Long attrId) {
        AttrDetailVo detail = iAttrService.detail(attrId);
        return AjaxResult.success(detail);
    }

    /**
     * 商品属性新增
     *
     * @param attrParam 参数
     * @return Object
     */
    @Log(title = "商品属性新增")
    @PostMapping("/add")
    public Object add(@Validated(value = AttrParam.create.class) @RequestBody AttrParam attrParam) {
        iAttrService.add(attrParam);
        return AjaxResult.success();
    }

    /**
     * 商品属性编辑
     *
     * @param attrParam 参数
     * @return Object
     */
    @Log(title = "商品属性编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = AttrParam.update.class) @RequestBody AttrParam attrParam) {
        iAttrService.edit(attrParam);
        return AjaxResult.success();
    }


    /**
     * 商品属性批量删除
     *
     * @param attrIds 参数
     * @return Object
     */
    @Log(title = "商品属性批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] attrIds) {
        iAttrService.removeByIds(Arrays.asList(attrIds));
        return AjaxResult.success();
    }

    /**
     * 商品属性编辑
     *
     * @param attrDetailVo 参数
     * @return Object
     */
    @Log(title = "商品属性编辑")
    @PostMapping("/update")
    public Object update(@RequestBody AttrDetailVo attrDetailVo) {
        iAttrService.update(attrDetailVo);
        return AjaxResult.success();
    }

    /**
     * 商品属性新增
     *
     * @param attrVo 参数
     * @return Object
     */
    @Log(title = "商品属性新增")
    @PostMapping("/save")
    public Object save(@RequestBody AttrListVo attrVo) {
        iAttrService.save(attrVo);
        return AjaxResult.success();
    }

    @Log(title = "查出商品的属性")
    @GetMapping("/listforspu")
    public Object listforspu(@RequestParam("spuId") Long spuId,
                             @RequestParam("attrType") Integer attrType){
        List<ProductAttrValue> entities = iProductAttrValueService.attrlistforspu(spuId);
        return AjaxResult.success(entities);
    }

    @PostMapping("/updateSpuAttr")
    public Object updateSpuAttr(@RequestBody List<ProductAttrValue> entities){
        iProductAttrValueService.updateSpuAttr(entities);
        return AjaxResult.success(entities);
    }
}
//TODO 删除全部包括关联数据
//TODO 查询 查询全部