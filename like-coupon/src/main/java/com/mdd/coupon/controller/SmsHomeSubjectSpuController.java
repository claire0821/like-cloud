package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsHomeSubjectSpuService;
import com.mdd.coupon.validate.SmsHomeSubjectSpuParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsHomeSubjectSpuListVo;
import com.mdd.coupon.vo.SmsHomeSubjectSpuDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 专题商品管理
 */
@RestController
@RequestMapping("api/spu")
public class SmsHomeSubjectSpuController {

    @Resource
    ISmsHomeSubjectSpuService iSmsHomeSubjectSpuService;

    /**
     * 专题商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsHomeSubjectSpuListVo> list = iSmsHomeSubjectSpuService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 专题商品详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsHomeSubjectSpuDetailVo detail = iSmsHomeSubjectSpuService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 专题商品新增
     *
     * @param smsHomeSubjectSpuParam 参数
     * @return Object
     */
    @Log(title = "专题商品新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsHomeSubjectSpuParam.create.class) @RequestBody SmsHomeSubjectSpuParam smsHomeSubjectSpuParam) {
        iSmsHomeSubjectSpuService.add(smsHomeSubjectSpuParam);
        return AjaxResult.success();
    }

    /**
     * 专题商品编辑
     *
     * @param smsHomeSubjectSpuParam 参数
     * @return Object
     */
    @Log(title = "专题商品编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsHomeSubjectSpuParam.update.class) @RequestBody SmsHomeSubjectSpuParam smsHomeSubjectSpuParam) {
        iSmsHomeSubjectSpuService.edit(smsHomeSubjectSpuParam);
        return AjaxResult.success();
    }

    /**
     * 专题商品删除
     *
     * @param smsHomeSubjectSpuParam 参数
     * @return Object
     */
    @Log(title = "专题商品删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsHomeSubjectSpuParam.delete.class) @RequestBody SmsHomeSubjectSpuParam smsHomeSubjectSpuParam) {
        iSmsHomeSubjectSpuService.del(smsHomeSubjectSpuParam.getId());
        return AjaxResult.success();
    }

}
