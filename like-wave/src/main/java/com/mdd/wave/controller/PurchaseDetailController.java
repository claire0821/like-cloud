package com.mdd.wave.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.wave.service.IPurchaseDetailService;
import com.mdd.wave.validate.PurchaseDetailParam;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.vo.PurchaseDetailListVo;
import com.mdd.wave.vo.PurchaseDetailDetailVo;
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
 * 【请填写功能名称】管理
 */
@RestController
@RequestMapping("api/wave/detail")
public class PurchaseDetailController {

    @Resource
    IPurchaseDetailService iPurchaseDetailService;

    /**
     * 【请填写功能名称】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PurchaseDetailListVo> list = iPurchaseDetailService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 【请填写功能名称】详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        PurchaseDetailDetailVo detail = iPurchaseDetailService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 【请填写功能名称】新增
     *
     * @param purchaseDetailParam 参数
     * @return Object
     */
    @Log(title = "【请填写功能名称】新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PurchaseDetailParam.create.class) @RequestBody PurchaseDetailParam purchaseDetailParam) {
        iPurchaseDetailService.add(purchaseDetailParam);
        return AjaxResult.success();
    }

    /**
     * 【请填写功能名称】编辑
     *
     * @param purchaseDetailParam 参数
     * @return Object
     */
    @Log(title = "【请填写功能名称】编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PurchaseDetailParam.update.class) @RequestBody PurchaseDetailParam purchaseDetailParam) {
        iPurchaseDetailService.edit(purchaseDetailParam);
        return AjaxResult.success();
    }


    /**
     * 【请填写功能名称】批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "【请填写功能名称】批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iPurchaseDetailService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
