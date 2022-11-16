package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ICouponHistoryService;
import com.mdd.coupon.validate.CouponHistoryParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.CouponHistoryListVo;
import com.mdd.coupon.vo.CouponHistoryDetailVo;
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
 * 优惠券领取历史记录管理
 */
@RestController
@RequestMapping("api/coupon/history")
public class CouponHistoryController {

    @Resource
    ICouponHistoryService iCouponHistoryService;

    /**
     * 优惠券领取历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<CouponHistoryListVo> list = iCouponHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券领取历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        CouponHistoryDetailVo detail = iCouponHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券领取历史记录新增
     *
     * @param couponHistoryParam 参数
     * @return Object
     */
    @Log(title = "优惠券领取历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CouponHistoryParam.create.class) @RequestBody CouponHistoryParam couponHistoryParam) {
        iCouponHistoryService.add(couponHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券领取历史记录编辑
     *
     * @param couponHistoryParam 参数
     * @return Object
     */
    @Log(title = "优惠券领取历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CouponHistoryParam.update.class) @RequestBody CouponHistoryParam couponHistoryParam) {
        iCouponHistoryService.edit(couponHistoryParam);
        return AjaxResult.success();
    }


    /**
     * 优惠券领取历史记录批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "优惠券领取历史记录批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iCouponHistoryService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
