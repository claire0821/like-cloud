package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ICouponSpuCategoryRelationService;
import com.mdd.coupon.validate.CouponSpuCategoryRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.CouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.CouponSpuCategoryRelationDetailVo;
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
 * 优惠券分类关联管理
 */
@RestController
@RequestMapping("api/coupon/spucategoryrelation")
public class CouponSpuCategoryRelationController {

    @Resource
    ICouponSpuCategoryRelationService iCouponSpuCategoryRelationService;

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
        PageResult<CouponSpuCategoryRelationListVo> list = iCouponSpuCategoryRelationService.list(pageParam, params);
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
        CouponSpuCategoryRelationDetailVo detail = iCouponSpuCategoryRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券分类关联新增
     *
     * @param couponSpuCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CouponSpuCategoryRelationParam.create.class) @RequestBody CouponSpuCategoryRelationParam couponSpuCategoryRelationParam) {
        iCouponSpuCategoryRelationService.add(couponSpuCategoryRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券分类关联编辑
     *
     * @param couponSpuCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CouponSpuCategoryRelationParam.update.class) @RequestBody CouponSpuCategoryRelationParam couponSpuCategoryRelationParam) {
        iCouponSpuCategoryRelationService.edit(couponSpuCategoryRelationParam);
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
        iCouponSpuCategoryRelationService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
