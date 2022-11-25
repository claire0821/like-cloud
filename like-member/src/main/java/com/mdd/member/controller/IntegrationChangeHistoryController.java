package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IIntegrationChangeHistoryService;
import com.mdd.member.validate.IntegrationChangeHistoryParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.IntegrationChangeHistoryListVo;
import com.mdd.member.vo.IntegrationChangeHistoryDetailVo;
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
 * 积分变化历史记录管理
 */
@RestController
@RequestMapping("api/member/integrationchangehistory")
public class IntegrationChangeHistoryController {

    @Resource
    IIntegrationChangeHistoryService iIntegrationChangeHistoryService;

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
        PageResult<IntegrationChangeHistoryListVo> list = iIntegrationChangeHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 积分变化历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        IntegrationChangeHistoryDetailVo detail = iIntegrationChangeHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 积分变化历史记录新增
     *
     * @param integrationChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "积分变化历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = IntegrationChangeHistoryParam.create.class) @RequestBody IntegrationChangeHistoryParam integrationChangeHistoryParam) {
        iIntegrationChangeHistoryService.add(integrationChangeHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 积分变化历史记录编辑
     *
     * @param integrationChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "积分变化历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = IntegrationChangeHistoryParam.update.class) @RequestBody IntegrationChangeHistoryParam integrationChangeHistoryParam) {
        iIntegrationChangeHistoryService.edit(integrationChangeHistoryParam);
        return AjaxResult.success();
    }


    /**
     * 积分变化历史记录批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "积分变化历史记录批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iIntegrationChangeHistoryService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
