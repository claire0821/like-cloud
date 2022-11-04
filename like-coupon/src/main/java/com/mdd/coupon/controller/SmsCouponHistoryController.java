package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsCouponHistoryService;
import com.mdd.coupon.validate.SmsCouponHistoryParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsCouponHistoryListVo;
import com.mdd.coupon.vo.SmsCouponHistoryDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 优惠券领取历史记录管理
 */
@RestController
@RequestMapping("api/history")
public class SmsCouponHistoryController {

    @Resource
    ISmsCouponHistoryService iSmsCouponHistoryService;

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
        PageResult<SmsCouponHistoryListVo> list = iSmsCouponHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 优惠券领取历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsCouponHistoryDetailVo detail = iSmsCouponHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 优惠券领取历史记录新增
     *
     * @param smsCouponHistoryParam 参数
     * @return Object
     */
    @Log(title = "优惠券领取历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsCouponHistoryParam.create.class) @RequestBody SmsCouponHistoryParam smsCouponHistoryParam) {
        iSmsCouponHistoryService.add(smsCouponHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券领取历史记录编辑
     *
     * @param smsCouponHistoryParam 参数
     * @return Object
     */
    @Log(title = "优惠券领取历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsCouponHistoryParam.update.class) @RequestBody SmsCouponHistoryParam smsCouponHistoryParam) {
        iSmsCouponHistoryService.edit(smsCouponHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 优惠券领取历史记录删除
     *
     * @param smsCouponHistoryParam 参数
     * @return Object
     */
    @Log(title = "优惠券领取历史记录删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsCouponHistoryParam.delete.class) @RequestBody SmsCouponHistoryParam smsCouponHistoryParam) {
        iSmsCouponHistoryService.del(smsCouponHistoryParam.getId());
        return AjaxResult.success();
    }

}
