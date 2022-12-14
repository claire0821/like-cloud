package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IOrderReturnReasonService;
import com.mdd.order.validate.OrderReturnReasonParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.OrderReturnReasonListVo;
import com.mdd.order.vo.OrderReturnReasonDetailVo;
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
 * 退货原因管理
 */
@RestController
@RequestMapping("api/order/reason")
public class OrderReturnReasonController {

    @Resource
    IOrderReturnReasonService iOrderReturnReasonService;

    /**
     * 退货原因列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<OrderReturnReasonListVo> list = iOrderReturnReasonService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 退货原因详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        OrderReturnReasonDetailVo detail = iOrderReturnReasonService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 退货原因新增
     *
     * @param orderReturnReasonParam 参数
     * @return Object
     */
    @Log(title = "退货原因新增")
    @PostMapping("/add")
    public Object add(@Validated(value = OrderReturnReasonParam.create.class) @RequestBody OrderReturnReasonParam orderReturnReasonParam) {
        iOrderReturnReasonService.add(orderReturnReasonParam);
        return AjaxResult.success();
    }

    /**
     * 退货原因编辑
     *
     * @param orderReturnReasonParam 参数
     * @return Object
     */
    @Log(title = "退货原因编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = OrderReturnReasonParam.update.class) @RequestBody OrderReturnReasonParam orderReturnReasonParam) {
        iOrderReturnReasonService.edit(orderReturnReasonParam);
        return AjaxResult.success();
    }


    /**
     * 退货原因批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "退货原因批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iOrderReturnReasonService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
