package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ICouponService;
import com.mdd.coupon.validate.CouponParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.CouponListVo;
import com.mdd.coupon.vo.CouponDetailVo;
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
 * 优惠券信息管理
 */
@RestController
@RequestMapping("api/coupon/coupon")
public class CouponController {

    @Resource
    ICouponService iCouponService;

    /**
     * 优惠券信息列表
     *
     * @param params 搜索参数
     * @return Object
     */
    @PostMapping("/list")
    public Object list(@RequestBody Map<String, Object> params) {
        PageResult<CouponListVo> list = iCouponService.list(params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券信息详情
     *
     * @param code 优惠码
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@RequestParam("code") String code) {
        CouponDetailVo detail = iCouponService.detail(code);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券信息新增
     *
     * @param couponParam 参数
     * @return Object
     */
    @Log(title = "优惠券信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CouponParam.create.class) @RequestBody CouponParam couponParam) {
        iCouponService.add(couponParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券信息编辑
     *
     * @param couponParam 参数
     * @return Object
     */
    @Log(title = "优惠券信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CouponParam.update.class) @RequestBody CouponParam couponParam) {
        iCouponService.edit(couponParam);
        return AjaxResult.success();
    }


    /**
     * 优惠券信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "优惠券信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iCouponService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
