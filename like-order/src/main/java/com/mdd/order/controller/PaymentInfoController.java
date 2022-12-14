package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IPaymentInfoService;
import com.mdd.order.validate.PaymentInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.PaymentInfoListVo;
import com.mdd.order.vo.PaymentInfoDetailVo;
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
 * 支付信息管理
 */
@RestController
@RequestMapping("api/order/payment")
public class PaymentInfoController {

    @Resource
    IPaymentInfoService iPaymentInfoService;

    /**
     * 支付信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PaymentInfoListVo> list = iPaymentInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 支付信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        PaymentInfoDetailVo detail = iPaymentInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 支付信息新增
     *
     * @param paymentInfoParam 参数
     * @return Object
     */
    @Log(title = "支付信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PaymentInfoParam.create.class) @RequestBody PaymentInfoParam paymentInfoParam) {
        iPaymentInfoService.add(paymentInfoParam);
        return AjaxResult.success();
    }

    /**
     * 支付信息编辑
     *
     * @param paymentInfoParam 参数
     * @return Object
     */
    @Log(title = "支付信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PaymentInfoParam.update.class) @RequestBody PaymentInfoParam paymentInfoParam) {
        iPaymentInfoService.edit(paymentInfoParam);
        return AjaxResult.success();
    }


    /**
     * 支付信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "支付信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iPaymentInfoService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
