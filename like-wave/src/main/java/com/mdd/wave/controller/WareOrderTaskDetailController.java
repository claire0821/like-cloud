package com.mdd.wave.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.wave.service.IWareOrderTaskDetailService;
import com.mdd.wave.validate.WareOrderTaskDetailParam;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.vo.WareOrderTaskDetailListVo;
import com.mdd.wave.vo.WareOrderTaskDetailDetailVo;
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
 * 库存工作单管理
 */
@RestController
@RequestMapping("api/wave/detail")
public class WareOrderTaskDetailController {

    @Resource
    IWareOrderTaskDetailService iWareOrderTaskDetailService;

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
        PageResult<WareOrderTaskDetailListVo> list = iWareOrderTaskDetailService.list(pageParam, params);
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
        WareOrderTaskDetailDetailVo detail = iWareOrderTaskDetailService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 库存工作单新增
     *
     * @param wareOrderTaskDetailParam 参数
     * @return Object
     */
    @Log(title = "库存工作单新增")
    @PostMapping("/add")
    public Object add(@Validated(value = WareOrderTaskDetailParam.create.class) @RequestBody WareOrderTaskDetailParam wareOrderTaskDetailParam) {
        iWareOrderTaskDetailService.add(wareOrderTaskDetailParam);
        return AjaxResult.success();
    }

    /**
     * 库存工作单编辑
     *
     * @param wareOrderTaskDetailParam 参数
     * @return Object
     */
    @Log(title = "库存工作单编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = WareOrderTaskDetailParam.update.class) @RequestBody WareOrderTaskDetailParam wareOrderTaskDetailParam) {
        iWareOrderTaskDetailService.edit(wareOrderTaskDetailParam);
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
        iWareOrderTaskDetailService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
