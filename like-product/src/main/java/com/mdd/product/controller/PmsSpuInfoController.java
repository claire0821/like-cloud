package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IPmsSpuInfoService;
import com.mdd.product.validate.PmsSpuInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsSpuInfoListVo;
import com.mdd.product.vo.PmsSpuInfoDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * spu信息管理
 */
@RestController
@RequestMapping("api/info")
public class PmsSpuInfoController {

    @Resource
    IPmsSpuInfoService iPmsSpuInfoService;

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsSpuInfoListVo> list = iPmsSpuInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSpuInfoDetailVo detail = iPmsSpuInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu信息新增
     *
     * @param pmsSpuInfoParam 参数
     * @return Object
     */
    @Log(title = "spu信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSpuInfoParam.create.class) @RequestBody PmsSpuInfoParam pmsSpuInfoParam) {
        iPmsSpuInfoService.add(pmsSpuInfoParam);
        return AjaxResult.success();
    }

    /**
     * spu信息编辑
     *
     * @param pmsSpuInfoParam 参数
     * @return Object
     */
    @Log(title = "spu信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSpuInfoParam.update.class) @RequestBody PmsSpuInfoParam pmsSpuInfoParam) {
        iPmsSpuInfoService.edit(pmsSpuInfoParam);
        return AjaxResult.success();
    }

    /**
     * spu信息删除
     *
     * @param pmsSpuInfoParam 参数
     * @return Object
     */
    @Log(title = "spu信息删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSpuInfoParam.delete.class) @RequestBody PmsSpuInfoParam pmsSpuInfoParam) {
        iPmsSpuInfoService.del(pmsSpuInfoParam.getId());
        return AjaxResult.success();
    }

}
