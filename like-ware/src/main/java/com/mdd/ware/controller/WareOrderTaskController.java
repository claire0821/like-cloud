package com.mdd.ware.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.ware.service.IWareOrderTaskService;
import com.mdd.ware.validate.WareOrderTaskParam;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.vo.WareOrderTaskListVo;
import com.mdd.ware.vo.WareOrderTaskDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 库存工作单管理
 */
@RestController
@RequestMapping("api/ware/task")
public class WareOrderTaskController {

    @Resource
    IWareOrderTaskService iWareOrderTaskService;

    /**
     * 库存工作单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<WareOrderTaskListVo> list = iWareOrderTaskService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 库存工作单详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        WareOrderTaskDetailVo detail = iWareOrderTaskService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 库存工作单新增
     *
     * @param wareOrderTaskParam 参数
     * @return Object
     */
    @Log(title = "库存工作单新增")
    @PostMapping("/add")
    public Object add(@Validated(value = WareOrderTaskParam.create.class) @RequestBody WareOrderTaskParam wareOrderTaskParam) {
        iWareOrderTaskService.add(wareOrderTaskParam);
        return AjaxResult.success();
    }

    /**
     * 库存工作单编辑
     *
     * @param wareOrderTaskParam 参数
     * @return Object
     */
    @Log(title = "库存工作单编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = WareOrderTaskParam.update.class) @RequestBody WareOrderTaskParam wareOrderTaskParam) {
        iWareOrderTaskService.edit(wareOrderTaskParam);
        return AjaxResult.success();
    }


    /**
     * 库存工作单批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "库存工作单批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iWareOrderTaskService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
