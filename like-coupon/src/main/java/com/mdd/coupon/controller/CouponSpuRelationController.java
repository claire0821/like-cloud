package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ICouponSpuRelationService;
import com.mdd.coupon.validate.CouponSpuRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.CouponSpuRelationListVo;
import com.mdd.coupon.vo.CouponSpuRelationDetailVo;
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
 * 优惠券与产品关联管理
 */
@RestController
@RequestMapping("api/coupon/spurelation")
public class CouponSpuRelationController {

    @Resource
    ICouponSpuRelationService iCouponSpuRelationService;

    /**
     * 优惠券与产品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<CouponSpuRelationListVo> list = iCouponSpuRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券与产品关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        CouponSpuRelationDetailVo detail = iCouponSpuRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券与产品关联新增
     *
     * @param couponSpuRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券与产品关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CouponSpuRelationParam.create.class) @RequestBody CouponSpuRelationParam couponSpuRelationParam) {
        iCouponSpuRelationService.add(couponSpuRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券与产品关联编辑
     *
     * @param couponSpuRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券与产品关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CouponSpuRelationParam.update.class) @RequestBody CouponSpuRelationParam couponSpuRelationParam) {
        iCouponSpuRelationService.edit(couponSpuRelationParam);
        return AjaxResult.success();
    }


    /**
     * 优惠券与产品关联批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "优惠券与产品关联批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iCouponSpuRelationService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
