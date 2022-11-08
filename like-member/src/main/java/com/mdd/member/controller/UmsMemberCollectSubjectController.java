package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IUmsMemberCollectSubjectService;
import com.mdd.member.validate.UmsMemberCollectSubjectParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.UmsMemberCollectSubjectListVo;
import com.mdd.member.vo.UmsMemberCollectSubjectDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员收藏的专题活动管理
 */
@RestController
@RequestMapping("api/subject")
public class UmsMemberCollectSubjectController {

    @Resource
    IUmsMemberCollectSubjectService iUmsMemberCollectSubjectService;

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
        PageResult<UmsMemberCollectSubjectListVo> list = iUmsMemberCollectSubjectService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员收藏的专题活动详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberCollectSubjectDetailVo detail = iUmsMemberCollectSubjectService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员收藏的专题活动新增
     *
     * @param umsMemberCollectSubjectParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的专题活动新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberCollectSubjectParam.create.class) @RequestBody UmsMemberCollectSubjectParam umsMemberCollectSubjectParam) {
        iUmsMemberCollectSubjectService.add(umsMemberCollectSubjectParam);
        return AjaxResult.success();
    }

    /**
     * 会员收藏的专题活动编辑
     *
     * @param umsMemberCollectSubjectParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的专题活动编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberCollectSubjectParam.update.class) @RequestBody UmsMemberCollectSubjectParam umsMemberCollectSubjectParam) {
        iUmsMemberCollectSubjectService.edit(umsMemberCollectSubjectParam);
        return AjaxResult.success();
    }

    /**
     * 会员收藏的专题活动删除
     *
     * @param umsMemberCollectSubjectParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的专题活动删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberCollectSubjectParam.delete.class) @RequestBody UmsMemberCollectSubjectParam umsMemberCollectSubjectParam) {
        iUmsMemberCollectSubjectService.del(umsMemberCollectSubjectParam.getId());
        return AjaxResult.success();
    }

}
