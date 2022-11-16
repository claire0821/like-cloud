package com.mdd.wave.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.wave.service.IPurchaseService;
import com.mdd.wave.validate.PurchaseParam;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.vo.PurchaseListVo;
import com.mdd.wave.vo.PurchaseDetailVo;
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
 * 采购信息管理
 */
@RestController
@RequestMapping("api/wave/purchase")
public class PurchaseController {

    @Resource
    IPurchaseService iPurchaseService;

    /**
     * 采购信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PurchaseListVo> list = iPurchaseService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 采购信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        PurchaseDetailVo detail = iPurchaseService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 采购信息新增
     *
     * @param purchaseParam 参数
     * @return Object
     */
    @Log(title = "采购信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PurchaseParam.create.class) @RequestBody PurchaseParam purchaseParam) {
        iPurchaseService.add(purchaseParam);
        return AjaxResult.success();
    }

    /**
     * 采购信息编辑
     *
     * @param purchaseParam 参数
     * @return Object
     */
    @Log(title = "采购信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PurchaseParam.update.class) @RequestBody PurchaseParam purchaseParam) {
        iPurchaseService.edit(purchaseParam);
        return AjaxResult.success();
    }


    /**
     * 采购信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "采购信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iPurchaseService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
