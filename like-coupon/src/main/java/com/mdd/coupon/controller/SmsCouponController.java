package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.entity.SmsCoupon;
import com.mdd.coupon.service.ISmsCouponService;
import com.mdd.coupon.validate.SmsCouponParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsCouponListVo;
import com.mdd.coupon.vo.SmsCouponDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券信息管理
 */
@RestController
@RequestMapping("api/smscoupon")
public class SmsCouponController {

    @Resource
    ISmsCouponService iSmsCouponService;

    @RequestMapping("/member/list")
    public Object membercoupons(){
        SmsCoupon couponEntity = new SmsCoupon();
        couponEntity.setCouponName("满100减10");
        return AjaxResult.success(couponEntity);
    }

    /**
     * 优惠券信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsCouponListVo> list = iSmsCouponService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsCouponDetailVo detail = iSmsCouponService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券信息新增
     *
     * @param smsCouponParam 参数
     * @return Object
     */
    @Log(title = "优惠券信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsCouponParam.create.class) @RequestBody SmsCouponParam smsCouponParam) {
        iSmsCouponService.add(smsCouponParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券信息编辑
     *
     * @param smsCouponParam 参数
     * @return Object
     */
    @Log(title = "优惠券信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsCouponParam.update.class) @RequestBody SmsCouponParam smsCouponParam) {
        iSmsCouponService.edit(smsCouponParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券信息删除
     *
     * @param smsCouponParam 参数
     * @return Object
     */
    @Log(title = "优惠券信息删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsCouponParam.delete.class) @RequestBody SmsCouponParam smsCouponParam) {
        iSmsCouponService.del(smsCouponParam.getId());
        return AjaxResult.success();
    }

}
