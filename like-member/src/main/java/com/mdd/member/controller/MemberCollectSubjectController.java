package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IMemberCollectSubjectService;
import com.mdd.member.validate.MemberCollectSubjectParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.MemberCollectSubjectListVo;
import com.mdd.member.vo.MemberCollectSubjectDetailVo;
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
 * 会员收藏的专题活动管理
 */
@RestController
@RequestMapping("api/member/subject")
public class MemberCollectSubjectController {

    @Resource
    IMemberCollectSubjectService iMemberCollectSubjectService;

    /**
     * 会员收藏的专题活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<MemberCollectSubjectListVo> list = iMemberCollectSubjectService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员收藏的专题活动详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberCollectSubjectDetailVo detail = iMemberCollectSubjectService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员收藏的专题活动新增
     *
     * @param memberCollectSubjectParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的专题活动新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberCollectSubjectParam.create.class) @RequestBody MemberCollectSubjectParam memberCollectSubjectParam) {
        iMemberCollectSubjectService.add(memberCollectSubjectParam);
        return AjaxResult.success();
    }

    /**
     * 会员收藏的专题活动编辑
     *
     * @param memberCollectSubjectParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的专题活动编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberCollectSubjectParam.update.class) @RequestBody MemberCollectSubjectParam memberCollectSubjectParam) {
        iMemberCollectSubjectService.edit(memberCollectSubjectParam);
        return AjaxResult.success();
    }


    /**
     * 会员收藏的专题活动批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员收藏的专题活动批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberCollectSubjectService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
