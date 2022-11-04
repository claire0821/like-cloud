package com.mdd.member.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.member.service.IUmsMemberLoginLogService;
import com.mdd.member.validate.UmsMemberLoginLogParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.vo.UmsMemberLoginLogListVo;
import com.mdd.member.vo.UmsMemberLoginLogDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员登录记录管理
 */
@RestController
@RequestMapping("api/log")
public class UmsMemberLoginLogController {

    @Resource
    IUmsMemberLoginLogService iUmsMemberLoginLogService;

    /**
     * 会员登录记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UmsMemberLoginLogListVo> list = iUmsMemberLoginLogService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员登录记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberLoginLogDetailVo detail = iUmsMemberLoginLogService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员登录记录新增
     *
     * @param umsMemberLoginLogParam 参数
     * @return Object
     */
    @Log(title = "会员登录记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberLoginLogParam.create.class) @RequestBody UmsMemberLoginLogParam umsMemberLoginLogParam) {
        iUmsMemberLoginLogService.add(umsMemberLoginLogParam);
        return AjaxResult.success();
    }

    /**
     * 会员登录记录编辑
     *
     * @param umsMemberLoginLogParam 参数
     * @return Object
     */
    @Log(title = "会员登录记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberLoginLogParam.update.class) @RequestBody UmsMemberLoginLogParam umsMemberLoginLogParam) {
        iUmsMemberLoginLogService.edit(umsMemberLoginLogParam);
        return AjaxResult.success();
    }

    /**
     * 会员登录记录删除
     *
     * @param umsMemberLoginLogParam 参数
     * @return Object
     */
    @Log(title = "会员登录记录删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberLoginLogParam.delete.class) @RequestBody UmsMemberLoginLogParam umsMemberLoginLogParam) {
        iUmsMemberLoginLogService.del(umsMemberLoginLogParam.getId());
        return AjaxResult.success();
    }

}
