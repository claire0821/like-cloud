package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ICouponCategoryRelationService;
import com.mdd.coupon.validate.CouponCategoryRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.CouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.CouponCategoryRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券分类关联管理
 */
@RestController
@RequestMapping("api/coupon/categoryrelation")
public class CouponCategoryRelationController {

    @Resource
    ICouponCategoryRelationService iCouponCategoryRelationService;

    /**
     * 优惠券分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<CouponSpuCategoryRelationListVo> list = iCouponCategoryRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券分类关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        CouponCategoryRelationDetailVo detail = iCouponCategoryRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券分类关联新增
     *
     * @param couponCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CouponCategoryRelationParam.create.class) @RequestBody CouponCategoryRelationParam couponCategoryRelationParam) {
        iCouponCategoryRelationService.add(couponCategoryRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券分类关联编辑
     *
     * @param couponCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CouponCategoryRelationParam.update.class) @RequestBody CouponCategoryRelationParam couponCategoryRelationParam) {
        iCouponCategoryRelationService.edit(couponCategoryRelationParam);
        return AjaxResult.success();
    }


    /**
     * 优惠券分类关联批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iCouponCategoryRelationService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
