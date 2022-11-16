package com.mdd.wave.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.wave.service.IWareInfoService;
import com.mdd.wave.validate.WareInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.vo.WareInfoListVo;
import com.mdd.wave.vo.WareInfoDetailVo;
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
 * 仓库信息管理
 */
@RestController
@RequestMapping("api/wave/info")
public class WareInfoController {

    @Resource
    IWareInfoService iWareInfoService;

    /**
     * 仓库信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<WareInfoListVo> list = iWareInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 仓库信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        WareInfoDetailVo detail = iWareInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 仓库信息新增
     *
     * @param wareInfoParam 参数
     * @return Object
     */
    @Log(title = "仓库信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = WareInfoParam.create.class) @RequestBody WareInfoParam wareInfoParam) {
        iWareInfoService.add(wareInfoParam);
        return AjaxResult.success();
    }

    /**
     * 仓库信息编辑
     *
     * @param wareInfoParam 参数
     * @return Object
     */
    @Log(title = "仓库信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = WareInfoParam.update.class) @RequestBody WareInfoParam wareInfoParam) {
        iWareInfoService.edit(wareInfoParam);
        return AjaxResult.success();
    }


    /**
     * 仓库信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "仓库信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iWareInfoService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
