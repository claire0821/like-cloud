package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.ISpuInfoDescService;
import com.mdd.product.validate.SpuInfoDescParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.SpuInfoDescListVo;
import com.mdd.product.vo.SpuInfoDescDetailVo;
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
 * spu信息介绍管理
 */
@RestController
@RequestMapping("api/product/desc")
public class SpuInfoDescController {

    @Resource
    ISpuInfoDescService iSpuInfoDescService;

    /**
     * spu信息介绍列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SpuInfoDescListVo> list = iSpuInfoDescService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu信息介绍详情
     *
     * @param spuId 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("spuId") Long spuId) {
        SpuInfoDescDetailVo detail = iSpuInfoDescService.detail(spuId);
        return AjaxResult.success(detail);
    }

    /**
     * spu信息介绍新增
     *
     * @param spuInfoDescParam 参数
     * @return Object
     */
    @Log(title = "spu信息介绍新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SpuInfoDescParam.create.class) @RequestBody SpuInfoDescParam spuInfoDescParam) {
        iSpuInfoDescService.add(spuInfoDescParam);
        return AjaxResult.success();
    }

    /**
     * spu信息介绍编辑
     *
     * @param spuInfoDescParam 参数
     * @return Object
     */
    @Log(title = "spu信息介绍编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SpuInfoDescParam.update.class) @RequestBody SpuInfoDescParam spuInfoDescParam) {
        iSpuInfoDescService.edit(spuInfoDescParam);
        return AjaxResult.success();
    }


    /**
     * spu信息介绍批量删除
     *
     * @param spuIds 参数
     * @return Object
     */
    @Log(title = "spu信息介绍批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] spuIds) {
        iSpuInfoDescService.removeByIds(Arrays.asList(spuIds));
        return AjaxResult.success();
    }

}
