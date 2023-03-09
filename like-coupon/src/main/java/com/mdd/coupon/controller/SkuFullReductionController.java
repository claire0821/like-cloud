package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.to.SkuReductionTo;
import com.mdd.coupon.service.ISkuFullReductionService;
import com.mdd.coupon.validate.SkuFullReductionParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SkuFullReductionListVo;
import com.mdd.coupon.vo.SkuFullReductionDetailVo;
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
 * 商品满减信息管理
 */
@RestController
@RequestMapping("api/coupon/reduction")
public class SkuFullReductionController {

    @Resource
    ISkuFullReductionService iSkuFullReductionService;

    /**
     * 商品满减信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SkuFullReductionListVo> list = iSkuFullReductionService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品满减信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SkuFullReductionDetailVo detail = iSkuFullReductionService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品满减信息新增
     *
     * @param skuFullReductionParam 参数
     * @return Object
     */
    @Log(title = "商品满减信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SkuFullReductionParam.create.class) @RequestBody SkuFullReductionParam skuFullReductionParam) {
        iSkuFullReductionService.add(skuFullReductionParam);
        return AjaxResult.success();
    }

    @PostMapping("/saveinfo")
    public Object saveInfo(@RequestBody SkuReductionTo reductionTo){
        iSkuFullReductionService.saveSkuReduction(reductionTo);
        return AjaxResult.success();
    }


    /**
     * 商品满减信息编辑
     *
     * @param skuFullReductionParam 参数
     * @return Object
     */
    @Log(title = "商品满减信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SkuFullReductionParam.update.class) @RequestBody SkuFullReductionParam skuFullReductionParam) {
        iSkuFullReductionService.edit(skuFullReductionParam);
        return AjaxResult.success();
    }


    /**
     * 商品满减信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品满减信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSkuFullReductionService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
