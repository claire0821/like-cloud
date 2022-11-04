package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsSpuBoundsService;
import com.mdd.coupon.validate.SmsSpuBoundsParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsSpuBoundsListVo;
import com.mdd.coupon.vo.SmsSpuBoundsDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品spu积分设置管理
 */
@RestController
@RequestMapping("api/bounds")
public class SmsSpuBoundsController {

    @Resource
    ISmsSpuBoundsService iSmsSpuBoundsService;

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
        PageResult<SmsSpuBoundsListVo> list = iSmsSpuBoundsService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品spu积分设置详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSpuBoundsDetailVo detail = iSmsSpuBoundsService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品spu积分设置新增
     *
     * @param smsSpuBoundsParam 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSpuBoundsParam.create.class) @RequestBody SmsSpuBoundsParam smsSpuBoundsParam) {
        iSmsSpuBoundsService.add(smsSpuBoundsParam);
        return AjaxResult.success();
    }

    /**
     * 商品spu积分设置编辑
     *
     * @param smsSpuBoundsParam 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSpuBoundsParam.update.class) @RequestBody SmsSpuBoundsParam smsSpuBoundsParam) {
        iSmsSpuBoundsService.edit(smsSpuBoundsParam);
        return AjaxResult.success();
    }

    /**
     * 商品spu积分设置删除
     *
     * @param smsSpuBoundsParam 参数
     * @return Object
     */
    @Log(title = "商品spu积分设置删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSpuBoundsParam.delete.class) @RequestBody SmsSpuBoundsParam smsSpuBoundsParam) {
        iSmsSpuBoundsService.del(smsSpuBoundsParam.getId());
        return AjaxResult.success();
    }

}
