package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IUmsMemberLevelService;
import com.mdd.member.validate.UmsMemberLevelParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.UmsMemberLevelListVo;
import com.mdd.member.vo.UmsMemberLevelDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员等级管理
 */
@RestController
@RequestMapping("api/level")
public class UmsMemberLevelController {

    @Resource
    IUmsMemberLevelService iUmsMemberLevelService;

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
        PageResult<UmsMemberLevelListVo> list = iUmsMemberLevelService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员等级详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberLevelDetailVo detail = iUmsMemberLevelService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员等级新增
     *
     * @param umsMemberLevelParam 参数
     * @return Object
     */
    @Log(title = "会员等级新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberLevelParam.create.class) @RequestBody UmsMemberLevelParam umsMemberLevelParam) {
        iUmsMemberLevelService.add(umsMemberLevelParam);
        return AjaxResult.success();
    }

    /**
     * 会员等级编辑
     *
     * @param umsMemberLevelParam 参数
     * @return Object
     */
    @Log(title = "会员等级编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberLevelParam.update.class) @RequestBody UmsMemberLevelParam umsMemberLevelParam) {
        iUmsMemberLevelService.edit(umsMemberLevelParam);
        return AjaxResult.success();
    }

    /**
     * 会员等级删除
     *
     * @param umsMemberLevelParam 参数
     * @return Object
     */
    @Log(title = "会员等级删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberLevelParam.delete.class) @RequestBody UmsMemberLevelParam umsMemberLevelParam) {
        iUmsMemberLevelService.del(umsMemberLevelParam.getId());
        return AjaxResult.success();
    }

}
