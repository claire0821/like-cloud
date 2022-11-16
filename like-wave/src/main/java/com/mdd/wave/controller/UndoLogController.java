package com.mdd.wave.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.wave.service.IUndoLogService;
import com.mdd.wave.validate.UndoLogParam;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.vo.UndoLogListVo;
import com.mdd.wave.vo.UndoLogDetailVo;
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
 * 【请填写功能名称】管理
 */
@RestController
@RequestMapping("api/wave/log")
public class UndoLogController {

    @Resource
    IUndoLogService iUndoLogService;

    /**
     * 【请填写功能名称】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UndoLogListVo> list = iUndoLogService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 【请填写功能名称】详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        UndoLogDetailVo detail = iUndoLogService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 【请填写功能名称】新增
     *
     * @param undoLogParam 参数
     * @return Object
     */
    @Log(title = "【请填写功能名称】新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UndoLogParam.create.class) @RequestBody UndoLogParam undoLogParam) {
        iUndoLogService.add(undoLogParam);
        return AjaxResult.success();
    }

    /**
     * 【请填写功能名称】编辑
     *
     * @param undoLogParam 参数
     * @return Object
     */
    @Log(title = "【请填写功能名称】编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UndoLogParam.update.class) @RequestBody UndoLogParam undoLogParam) {
        iUndoLogService.edit(undoLogParam);
        return AjaxResult.success();
    }


    /**
     * 【请填写功能名称】批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "【请填写功能名称】批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iUndoLogService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
