package com.mdd.member.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.member.service.IUmsMemberStatisticsInfoService;
import com.mdd.member.validate.UmsMemberStatisticsInfoParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.vo.UmsMemberStatisticsInfoListVo;
import com.mdd.member.vo.UmsMemberStatisticsInfoDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员统计信息管理
 */
@RestController
@RequestMapping("api/info")
public class UmsMemberStatisticsInfoController {

    @Resource
    IUmsMemberStatisticsInfoService iUmsMemberStatisticsInfoService;

    /**
     * 会员统计信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UmsMemberStatisticsInfoListVo> list = iUmsMemberStatisticsInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员统计信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberStatisticsInfoDetailVo detail = iUmsMemberStatisticsInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员统计信息新增
     *
     * @param umsMemberStatisticsInfoParam 参数
     * @return Object
     */
    @Log(title = "会员统计信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberStatisticsInfoParam.create.class) @RequestBody UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam) {
        iUmsMemberStatisticsInfoService.add(umsMemberStatisticsInfoParam);
        return AjaxResult.success();
    }

    /**
     * 会员统计信息编辑
     *
     * @param umsMemberStatisticsInfoParam 参数
     * @return Object
     */
    @Log(title = "会员统计信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberStatisticsInfoParam.update.class) @RequestBody UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam) {
        iUmsMemberStatisticsInfoService.edit(umsMemberStatisticsInfoParam);
        return AjaxResult.success();
    }

    /**
     * 会员统计信息删除
     *
     * @param umsMemberStatisticsInfoParam 参数
     * @return Object
     */
    @Log(title = "会员统计信息删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberStatisticsInfoParam.delete.class) @RequestBody UmsMemberStatisticsInfoParam umsMemberStatisticsInfoParam) {
        iUmsMemberStatisticsInfoService.del(umsMemberStatisticsInfoParam.getId());
        return AjaxResult.success();
    }

}
