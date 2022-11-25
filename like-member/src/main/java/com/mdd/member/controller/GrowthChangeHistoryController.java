package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IGrowthChangeHistoryService;
import com.mdd.member.validate.GrowthChangeHistoryParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.GrowthChangeHistoryListVo;
import com.mdd.member.vo.GrowthChangeHistoryDetailVo;
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
 * 成长值变化历史记录管理
 */
@RestController
@RequestMapping("api/member/history")
public class GrowthChangeHistoryController {

    @Resource
    IGrowthChangeHistoryService iGrowthChangeHistoryService;

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
        PageResult<GrowthChangeHistoryListVo> list = iGrowthChangeHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 成长值变化历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        GrowthChangeHistoryDetailVo detail = iGrowthChangeHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 成长值变化历史记录新增
     *
     * @param growthChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "成长值变化历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = GrowthChangeHistoryParam.create.class) @RequestBody GrowthChangeHistoryParam growthChangeHistoryParam) {
        iGrowthChangeHistoryService.add(growthChangeHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 成长值变化历史记录编辑
     *
     * @param growthChangeHistoryParam 参数
     * @return Object
     */
    @Log(title = "成长值变化历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = GrowthChangeHistoryParam.update.class) @RequestBody GrowthChangeHistoryParam growthChangeHistoryParam) {
        iGrowthChangeHistoryService.edit(growthChangeHistoryParam);
        return AjaxResult.success();
    }


    /**
     * 成长值变化历史记录批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "成长值变化历史记录批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iGrowthChangeHistoryService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
