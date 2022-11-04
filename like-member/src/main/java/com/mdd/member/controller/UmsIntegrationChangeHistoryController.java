package com.mdd.member.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.member.service.IUmsIntegrationChangeHistoryService;
import com.mdd.member.validate.UmsIntegrationChangeHistoryParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.vo.UmsIntegrationChangeHistoryListVo;
import com.mdd.member.vo.UmsIntegrationChangeHistoryDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 积分变化历史记录管理
 */
@RestController
@RequestMapping("api/integrationchangehistory")
public class UmsIntegrationChangeHistoryController {

    @Resource
    IUmsIntegrationChangeHistoryService iUmsIntegrationChangeHistoryService;

    /**
     * 积分变化历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UmsIntegrationChangeHistoryListVo> list = iUmsIntegrationChangeHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 积分变化历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsIntegrationChangeHistoryDetailVo detail = iUmsIntegrationChangeHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 积分变化历史记录新增
     *
     * @param umsIntegrationChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "积分变化历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsIntegrationChangeHistoryParam.create.class) @RequestBody UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam) {
        iUmsIntegrationChangeHistoryService.add(umsIntegrationChangeHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 积分变化历史记录编辑
     *
     * @param umsIntegrationChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "积分变化历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsIntegrationChangeHistoryParam.update.class) @RequestBody UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam) {
        iUmsIntegrationChangeHistoryService.edit(umsIntegrationChangeHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 积分变化历史记录删除
     *
     * @param umsIntegrationChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "积分变化历史记录删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsIntegrationChangeHistoryParam.delete.class) @RequestBody UmsIntegrationChangeHistoryParam umsIntegrationChangeHistoryParam) {
        iUmsIntegrationChangeHistoryService.del(umsIntegrationChangeHistoryParam.getId());
        return AjaxResult.success();
    }

}
