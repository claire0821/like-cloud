package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.entity.SpuBounds;
import com.mdd.coupon.service.ISpuBoundsService;
import com.mdd.coupon.validate.SpuBoundsParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SpuBoundsListVo;
import com.mdd.coupon.vo.SpuBoundsDetailVo;
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
 * 商品spu积分设置管理
 */
@RestController
@RequestMapping("api/coupon/spubounds")
public class SpuBoundsController {

    @Resource
    ISpuBoundsService iSpuBoundsService;

    /**
     * 商品spu积分设置列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SpuBoundsListVo> list = iSpuBoundsService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品spu积分设置详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SpuBoundsDetailVo detail = iSpuBoundsService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品spu积分设置新增
     *
     * @param spuBoundsParam 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SpuBoundsParam.create.class) @RequestBody SpuBoundsParam spuBoundsParam) {
        iSpuBoundsService.add(spuBoundsParam);
        return AjaxResult.success();
    }

    /**
     * 商品spu积分设置新增
     *
     * @param spuBounds 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置新增")
    @PostMapping("/save")
    public Object save(@RequestBody SpuBounds spuBounds){
        iSpuBoundsService.save(spuBounds);
        return AjaxResult.success();
    }

    /**
     * 商品spu积分设置编辑
     *
     * @param spuBoundsParam 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SpuBoundsParam.update.class) @RequestBody SpuBoundsParam spuBoundsParam) {
        iSpuBoundsService.edit(spuBoundsParam);
        return AjaxResult.success();
    }


    /**
     * 商品spu积分设置批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSpuBoundsService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
