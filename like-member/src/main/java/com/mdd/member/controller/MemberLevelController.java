package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IMemberLevelService;
import com.mdd.member.validate.MemberLevelParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.MemberLevelListVo;
import com.mdd.member.vo.MemberLevelDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 会员等级管理
 */
@RestController
@RequestMapping("api/member/level")
public class MemberLevelController {

    @Resource
    IMemberLevelService iMemberLevelService;

    /**
     * 会员等级列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<MemberLevelListVo> list = iMemberLevelService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员等级详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberLevelDetailVo detail = iMemberLevelService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员等级新增
     *
     * @param memberLevelParam 参数
     * @return Object
     */
    @Log(title = "会员等级新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberLevelParam.create.class) @RequestBody MemberLevelParam memberLevelParam) {
        iMemberLevelService.add(memberLevelParam);
        return AjaxResult.success();
    }

    /**
     * 会员等级编辑
     *
     * @param memberLevelParam 参数
     * @return Object
     */
    @Log(title = "会员等级编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberLevelParam.update.class) @RequestBody MemberLevelParam memberLevelParam) {
        iMemberLevelService.edit(memberLevelParam);
        return AjaxResult.success();
    }


    /**
     * 会员等级批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员等级批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberLevelService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    /**
     * 会员等级名称和id列表
     *
     * @return Object
     */
    @Log(title = "会员等级名称和id列表")
    @GetMapping("/getLevel")
    public AjaxResult<List<MemberLevelListVo>> getLevel() {
        final List<MemberLevelListVo> level = iMemberLevelService.getLevel();
        return AjaxResult.success(level);
    }
}
