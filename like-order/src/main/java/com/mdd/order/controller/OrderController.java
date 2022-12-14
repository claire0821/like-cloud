package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IOrderService;
import com.mdd.order.to.OrderCreateTo;
import com.mdd.order.validate.OrderParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.OrderListVo;
import com.mdd.order.vo.OrderDetailVo;
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
 * 订单管理
 */
@RestController
@RequestMapping("api/order/order")
public class OrderController {

    @Resource
    IOrderService iOrderService;

    /**
     * 订单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<OrderListVo> list = iOrderService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 订单详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        OrderDetailVo detail = iOrderService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 订单新增
     *
     * @param orderParam 参数
     * @return Object
     */
    @Log(title = "订单新增")
    @PostMapping("/add")
    public Object add(@Validated(value = OrderParam.create.class) @RequestBody OrderParam orderParam) {
        iOrderService.add(orderParam);
        return AjaxResult.success();
    }

    /**
     * 订单编辑
     *
     * @param orderParam 参数
     * @return Object
     */
    @Log(title = "订单编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = OrderParam.update.class) @RequestBody OrderParam orderParam) {
        iOrderService.edit(orderParam);
        return AjaxResult.success();
    }


    /**
     * 订单批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "订单批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iOrderService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    /**
     * 确认订单
     *
     * @return Object
     */
    @Log(title = "确认订单")
    @PostMapping("/confirm")
    public Object confirm() {
        OrderCreateTo orderCreateTo = iOrderService.createOrder();
        return AjaxResult.success(orderCreateTo);
    }
}
