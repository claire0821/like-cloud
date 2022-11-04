package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsSkuFullReductionService;
import com.mdd.coupon.validate.SmsSkuFullReductionParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsSkuFullReductionListVo;
import com.mdd.coupon.vo.SmsSkuFullReductionDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品满减信息管理
 */
@RestController
@RequestMapping("api/reduction")
public class SmsSkuFullReductionController {

    @Resource
    ISmsSkuFullReductionService iSmsSkuFullReductionService;

    /**
     * 商品满减信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsSkuFullReductionListVo> list = iSmsSkuFullReductionService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品满减信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSkuFullReductionDetailVo detail = iSmsSkuFullReductionService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品满减信息新增
     *
     * @param smsSkuFullReductionParam 参数
     * @return Object
     */
    @Log(title = "商品满减信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSkuFullReductionParam.create.class) @RequestBody SmsSkuFullReductionParam smsSkuFullReductionParam) {
        iSmsSkuFullReductionService.add(smsSkuFullReductionParam);
        return AjaxResult.success();
    }

    /**
     * 商品满减信息编辑
     *
     * @param smsSkuFullReductionParam 参数
     * @return Object
     */
    @Log(title = "商品满减信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSkuFullReductionParam.update.class) @RequestBody SmsSkuFullReductionParam smsSkuFullReductionParam) {
        iSmsSkuFullReductionService.edit(smsSkuFullReductionParam);
        return AjaxResult.success();
    }

    /**
     * 商品满减信息删除
     *
     * @param smsSkuFullReductionParam 参数
     * @return Object
     */
    @Log(title = "商品满减信息删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSkuFullReductionParam.delete.class) @RequestBody SmsSkuFullReductionParam smsSkuFullReductionParam) {
        iSmsSkuFullReductionService.del(smsSkuFullReductionParam.getId());
        return AjaxResult.success();
    }

}
