package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsCouponSpuRelationService;
import com.mdd.coupon.validate.SmsCouponSpuRelationParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsCouponSpuRelationListVo;
import com.mdd.coupon.vo.SmsCouponSpuRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 优惠券与产品关联管理
 */
@RestController
@RequestMapping("api/spurelation")
public class SmsCouponSpuRelationController {

    @Resource
    ISmsCouponSpuRelationService iSmsCouponSpuRelationService;

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
        PageResult<SmsCouponSpuRelationListVo> list = iSmsCouponSpuRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券与产品关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsCouponSpuRelationDetailVo detail = iSmsCouponSpuRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券与产品关联新增
     *
     * @param smsCouponSpuRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券与产品关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsCouponSpuRelationParam.create.class) @RequestBody SmsCouponSpuRelationParam smsCouponSpuRelationParam) {
        iSmsCouponSpuRelationService.add(smsCouponSpuRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券与产品关联编辑
     *
     * @param smsCouponSpuRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券与产品关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsCouponSpuRelationParam.update.class) @RequestBody SmsCouponSpuRelationParam smsCouponSpuRelationParam) {
        iSmsCouponSpuRelationService.edit(smsCouponSpuRelationParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券与产品关联删除
     *
     * @param smsCouponSpuRelationParam 参数
     * @return Object
     */
    @Log(title = "优惠券与产品关联删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsCouponSpuRelationParam.delete.class) @RequestBody SmsCouponSpuRelationParam smsCouponSpuRelationParam) {
        iSmsCouponSpuRelationService.del(smsCouponSpuRelationParam.getId());
        return AjaxResult.success();
    }

}
