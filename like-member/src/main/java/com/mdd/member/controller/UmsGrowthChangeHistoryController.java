package com.mdd.member.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.member.service.IUmsGrowthChangeHistoryService;
import com.mdd.member.validate.UmsGrowthChangeHistoryParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.vo.UmsGrowthChangeHistoryListVo;
import com.mdd.member.vo.UmsGrowthChangeHistoryDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 成长值变化历史记录管理
 */
@RestController
@RequestMapping("api/growthchangehistory")
public class UmsGrowthChangeHistoryController {

    @Resource
    IUmsGrowthChangeHistoryService iUmsGrowthChangeHistoryService;

    /**
     * 成长值变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UmsGrowthChangeHistoryListVo> list = iUmsGrowthChangeHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 成长值变化历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsGrowthChangeHistoryDetailVo detail = iUmsGrowthChangeHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 成长值变化历史记录新增
     *
     * @param umsGrowthChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "成长值变化历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsGrowthChangeHistoryParam.create.class) @RequestBody UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam) {
        iUmsGrowthChangeHistoryService.add(umsGrowthChangeHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 成长值变化历史记录编辑
     *
     * @param umsGrowthChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "成长值变化历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsGrowthChangeHistoryParam.update.class) @RequestBody UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam) {
        iUmsGrowthChangeHistoryService.edit(umsGrowthChangeHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 成长值变化历史记录删除
     *
     * @param umsGrowthChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "成长值变化历史记录删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsGrowthChangeHistoryParam.delete.class) @RequestBody UmsGrowthChangeHistoryParam umsGrowthChangeHistoryParam) {
        iUmsGrowthChangeHistoryService.del(umsGrowthChangeHistoryParam.getId());
        return AjaxResult.success();
    }

}
