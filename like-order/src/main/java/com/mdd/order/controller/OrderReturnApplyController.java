package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IOrderReturnApplyService;
import com.mdd.order.validate.OrderReturnApplyParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.OrderReturnApplyListVo;
import com.mdd.order.vo.OrderReturnApplyDetailVo;
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
 * 订单退货申请管理
 */
@RestController
@RequestMapping("api/order/apply")
public class OrderReturnApplyController {

    @Resource
    IOrderReturnApplyService iOrderReturnApplyService;

    /**
     * 订单退货申请列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<OrderReturnApplyListVo> list = iOrderReturnApplyService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 订单退货申请详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        OrderReturnApplyDetailVo detail = iOrderReturnApplyService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 订单退货申请新增
     *
     * @param orderReturnApplyParam 参数
     * @return Object
     */
    @Log(title = "订单退货申请新增")
    @PostMapping("/add")
    public Object add(@Validated(value = OrderReturnApplyParam.create.class) @RequestBody OrderReturnApplyParam orderReturnApplyParam) {
        iOrderReturnApplyService.add(orderReturnApplyParam);
        return AjaxResult.success();
    }

    /**
     * 订单退货申请编辑
     *
     * @param orderReturnApplyParam 参数
     * @return Object
     */
    @Log(title = "订单退货申请编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = OrderReturnApplyParam.update.class) @RequestBody OrderReturnApplyParam orderReturnApplyParam) {
        iOrderReturnApplyService.edit(orderReturnApplyParam);
        return AjaxResult.success();
    }


    /**
     * 订单退货申请批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "订单退货申请批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iOrderReturnApplyService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
