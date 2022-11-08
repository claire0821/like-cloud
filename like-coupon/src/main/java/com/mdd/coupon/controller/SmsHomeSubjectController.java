package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsHomeSubjectService;
import com.mdd.coupon.validate.SmsHomeSubjectParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsHomeSubjectListVo;
import com.mdd.coupon.vo.SmsHomeSubjectDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】管理
 */
@RestController
@RequestMapping("api/subject")
public class SmsHomeSubjectController {

    @Resource
    ISmsHomeSubjectService iSmsHomeSubjectService;

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsHomeSubjectListVo> list = iSmsHomeSubjectService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsHomeSubjectDetailVo detail = iSmsHomeSubjectService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
     *
     * @param smsHomeSubjectParam 参数
     * @return Object
     */
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsHomeSubjectParam.create.class) @RequestBody SmsHomeSubjectParam smsHomeSubjectParam) {
        iSmsHomeSubjectService.add(smsHomeSubjectParam);
        return AjaxResult.success();
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
     *
     * @param smsHomeSubjectParam 参数
     * @return Object
     */
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsHomeSubjectParam.update.class) @RequestBody SmsHomeSubjectParam smsHomeSubjectParam) {
        iSmsHomeSubjectService.edit(smsHomeSubjectParam);
        return AjaxResult.success();
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】删除
     *
     * @param smsHomeSubjectParam 参数
     * @return Object
     */
    @Log(title = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsHomeSubjectParam.delete.class) @RequestBody SmsHomeSubjectParam smsHomeSubjectParam) {
        iSmsHomeSubjectService.del(smsHomeSubjectParam.getId());
        return AjaxResult.success();
    }

}
