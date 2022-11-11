package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IAttrService;
import com.mdd.product.validate.AttrParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.AttrListVo;
import com.mdd.product.vo.AttrDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品属性管理
 */
@RestController
@RequestMapping("api/product/attr")
public class AttrController {

    @Resource
    IAttrService iAttrService;

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
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated   @IDMust()  @RequestParam("id") Long attrId) {
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

}
