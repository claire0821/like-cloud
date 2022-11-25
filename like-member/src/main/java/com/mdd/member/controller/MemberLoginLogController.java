package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IMemberLoginLogService;
import com.mdd.member.validate.MemberLoginLogParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.MemberLoginLogListVo;
import com.mdd.member.vo.MemberLoginLogDetailVo;
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
 * 会员登录记录管理
 */
@RestController
@RequestMapping("api/member/log")
public class MemberLoginLogController {

    @Resource
    IMemberLoginLogService iMemberLoginLogService;

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
        PageResult<MemberLoginLogListVo> list = iMemberLoginLogService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员登录记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberLoginLogDetailVo detail = iMemberLoginLogService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员登录记录新增
     *
     * @param memberLoginLogParam 参数
     * @return Object
     */
    @Log(title = "会员登录记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberLoginLogParam.create.class) @RequestBody MemberLoginLogParam memberLoginLogParam) {
        iMemberLoginLogService.add(memberLoginLogParam);
        return AjaxResult.success();
    }

    /**
     * 会员登录记录编辑
     *
     * @param memberLoginLogParam 参数
     * @return Object
     */
    @Log(title = "会员登录记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberLoginLogParam.update.class) @RequestBody MemberLoginLogParam memberLoginLogParam) {
        iMemberLoginLogService.edit(memberLoginLogParam);
        return AjaxResult.success();
    }


    /**
     * 会员登录记录批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员登录记录批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberLoginLogService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
