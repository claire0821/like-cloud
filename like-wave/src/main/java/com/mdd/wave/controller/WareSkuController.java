package com.mdd.wave.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.wave.service.IWareSkuService;
import com.mdd.wave.validate.WareSkuParam;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.vo.WareSkuListVo;
import com.mdd.wave.vo.WareSkuDetailVo;
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
 * 商品库存管理
 */
@RestController
@RequestMapping("api/wave/sku")
public class WareSkuController {

    @Resource
    IWareSkuService iWareSkuService;

    /**
     * 商品库存列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<WareSkuListVo> list = iWareSkuService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品库存详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        WareSkuDetailVo detail = iWareSkuService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品库存新增
     *
     * @param wareSkuParam 参数
     * @return Object
     */
    @Log(title = "商品库存新增")
    @PostMapping("/add")
    public Object add(@Validated(value = WareSkuParam.create.class) @RequestBody WareSkuParam wareSkuParam) {
        iWareSkuService.add(wareSkuParam);
        return AjaxResult.success();
    }

    /**
     * 商品库存编辑
     *
     * @param wareSkuParam 参数
     * @return Object
     */
    @Log(title = "商品库存编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = WareSkuParam.update.class) @RequestBody WareSkuParam wareSkuParam) {
        iWareSkuService.edit(wareSkuParam);
        return AjaxResult.success();
    }


    /**
     * 商品库存批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品库存批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iWareSkuService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
