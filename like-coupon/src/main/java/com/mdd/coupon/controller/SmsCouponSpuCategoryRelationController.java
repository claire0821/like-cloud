package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsCouponSpuCategoryRelationService;
import com.mdd.coupon.validate.SmsCouponSpuCategoryRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsCouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.SmsCouponSpuCategoryRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 优惠券分类关联管理
 */
@RestController
@RequestMapping("api/relation")
public class SmsCouponSpuCategoryRelationController {

    @Resource
    ISmsCouponSpuCategoryRelationService iSmsCouponSpuCategoryRelationService;

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
        PageResult<SmsCouponSpuCategoryRelationListVo> list = iSmsCouponSpuCategoryRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券分类关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsCouponSpuCategoryRelationDetailVo detail = iSmsCouponSpuCategoryRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券分类关联新增
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsCouponSpuCategoryRelationParam.create.class) @RequestBody SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam) {
        iSmsCouponSpuCategoryRelationService.add(smsCouponSpuCategoryRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券分类关联编辑
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsCouponSpuCategoryRelationParam.update.class) @RequestBody SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam) {
        iSmsCouponSpuCategoryRelationService.edit(smsCouponSpuCategoryRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券分类关联删除
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券分类关联删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsCouponSpuCategoryRelationParam.delete.class) @RequestBody SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam) {
        iSmsCouponSpuCategoryRelationService.del(smsCouponSpuCategoryRelationParam.getId());
        return AjaxResult.success();
    }

}
