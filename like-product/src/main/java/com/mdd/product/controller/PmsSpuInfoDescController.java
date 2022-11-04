package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IPmsSpuInfoDescService;
import com.mdd.product.validate.PmsSpuInfoDescParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsSpuInfoDescListVo;
import com.mdd.product.vo.PmsSpuInfoDescDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * spu信息介绍管理
 */
@RestController
@RequestMapping("api/desc")
public class PmsSpuInfoDescController {

    @Resource
    IPmsSpuInfoDescService iPmsSpuInfoDescService;

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
        PageResult<PmsSpuInfoDescListVo> list = iPmsSpuInfoDescService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu信息介绍详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSpuInfoDescDetailVo detail = iPmsSpuInfoDescService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu信息介绍新增
     *
     * @param pmsSpuInfoDescParam 参数
     * @return Object
     */
    @Log(title = "spu信息介绍新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSpuInfoDescParam.create.class) @RequestBody PmsSpuInfoDescParam pmsSpuInfoDescParam) {
        iPmsSpuInfoDescService.add(pmsSpuInfoDescParam);
        return AjaxResult.success();
    }

    /**
     * spu信息介绍编辑
     *
     * @param pmsSpuInfoDescParam 参数
     * @return Object
     */
    @Log(title = "spu信息介绍编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSpuInfoDescParam.update.class) @RequestBody PmsSpuInfoDescParam pmsSpuInfoDescParam) {
        iPmsSpuInfoDescService.edit(pmsSpuInfoDescParam);
        return AjaxResult.success();
    }

    /**
     * spu信息介绍删除
     *
     * @param pmsSpuInfoDescParam 参数
     * @return Object
     */
    @Log(title = "spu信息介绍删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSpuInfoDescParam.delete.class) @RequestBody PmsSpuInfoDescParam pmsSpuInfoDescParam) {
        iPmsSpuInfoDescService.del(pmsSpuInfoDescParam.getSpuId());
        return AjaxResult.success();
    }

}
