package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsHomeAdvService;
import com.mdd.coupon.validate.SmsHomeAdvParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsHomeAdvListVo;
import com.mdd.coupon.vo.SmsHomeAdvDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 首页轮播广告管理
 */
@RestController
@RequestMapping("api/adv")
public class SmsHomeAdvController {

    @Resource
    ISmsHomeAdvService iSmsHomeAdvService;

    /**
     * 首页轮播广告列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsHomeAdvListVo> list = iSmsHomeAdvService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 首页轮播广告详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsHomeAdvDetailVo detail = iSmsHomeAdvService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 首页轮播广告新增
     *
     * @param smsHomeAdvParam 参数
     * @return Object
     */
    @Log(title = "首页轮播广告新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsHomeAdvParam.create.class) @RequestBody SmsHomeAdvParam smsHomeAdvParam) {
        iSmsHomeAdvService.add(smsHomeAdvParam);
        return AjaxResult.success();
    }

    /**
     * 首页轮播广告编辑
     *
     * @param smsHomeAdvParam 参数
     * @return Object
     */
    @Log(title = "首页轮播广告编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsHomeAdvParam.update.class) @RequestBody SmsHomeAdvParam smsHomeAdvParam) {
        iSmsHomeAdvService.edit(smsHomeAdvParam);
        return AjaxResult.success();
    }

    /**
     * 首页轮播广告删除
     *
     * @param smsHomeAdvParam 参数
     * @return Object
     */
    @Log(title = "首页轮播广告删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsHomeAdvParam.delete.class) @RequestBody SmsHomeAdvParam smsHomeAdvParam) {
        iSmsHomeAdvService.del(smsHomeAdvParam.getId());
        return AjaxResult.success();
    }

}
