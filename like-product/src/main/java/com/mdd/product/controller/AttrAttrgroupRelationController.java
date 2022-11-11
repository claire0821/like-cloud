package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IAttrAttrgroupRelationService;
import com.mdd.product.validate.AttrAttrgroupRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.AttrAttrgroupRelationListVo;
import com.mdd.product.vo.AttrAttrgroupRelationDetailVo;
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
 * 属性&属性分组关联管理
 */
@RestController
@RequestMapping("api/product/relation")
public class AttrAttrgroupRelationController {

    @Resource
    IAttrAttrgroupRelationService iAttrAttrgroupRelationService;

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
        PageResult<AttrAttrgroupRelationListVo> list = iAttrAttrgroupRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 属性&属性分组关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated   @IDMust()  @RequestParam("id") Long id) {
        AttrAttrgroupRelationDetailVo detail = iAttrAttrgroupRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 属性&属性分组关联新增
     *
     * @param attrAttrgroupRelationParam 参数
     * @return Object
     */
    @Log(title = "属性&属性分组关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = AttrAttrgroupRelationParam.create.class) @RequestBody AttrAttrgroupRelationParam attrAttrgroupRelationParam) {
        iAttrAttrgroupRelationService.add(attrAttrgroupRelationParam);
        return AjaxResult.success();
    }

    /**
     * 属性&属性分组关联编辑
     *
     * @param attrAttrgroupRelationParam 参数
     * @return Object
     */
    @Log(title = "属性&属性分组关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = AttrAttrgroupRelationParam.update.class) @RequestBody AttrAttrgroupRelationParam attrAttrgroupRelationParam) {
        iAttrAttrgroupRelationService.edit(attrAttrgroupRelationParam);
        return AjaxResult.success();
    }


    /**
     * 属性&属性分组关联批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "属性&属性分组关联批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iAttrAttrgroupRelationService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
