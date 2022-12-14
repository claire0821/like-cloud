package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IRefundInfoService;
import com.mdd.order.validate.RefundInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.RefundInfoListVo;
import com.mdd.order.vo.RefundInfoDetailVo;
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
 * 退款信息管理
 */
@RestController
@RequestMapping("api/order/info")
public class RefundInfoController {

    @Resource
    IRefundInfoService iRefundInfoService;

    /**
     * 退款信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<RefundInfoListVo> list = iRefundInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 退款信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        RefundInfoDetailVo detail = iRefundInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 退款信息新增
     *
     * @param refundInfoParam 参数
     * @return Object
     */
    @Log(title = "退款信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = RefundInfoParam.create.class) @RequestBody RefundInfoParam refundInfoParam) {
        iRefundInfoService.add(refundInfoParam);
        return AjaxResult.success();
    }

    /**
     * 退款信息编辑
     *
     * @param refundInfoParam 参数
     * @return Object
     */
    @Log(title = "退款信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = RefundInfoParam.update.class) @RequestBody RefundInfoParam refundInfoParam) {
        iRefundInfoService.edit(refundInfoParam);
        return AjaxResult.success();
    }


    /**
     * 退款信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "退款信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iRefundInfoService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
