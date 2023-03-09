package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.vo.OrderVo;
import com.mdd.order.entity.Order;
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
import com.mdd.order.vo.OrderSubmitVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 订单管理
 */
@RestController
@RequestMapping("api/order")
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
     * @param orderSn 订单编号
     * @return Object
     */
    @GetMapping("/detail")
    public AjaxResult<OrderCreateTo> detail(@Validated @IDLongMust() @RequestParam("orderSn") String orderSn) {
        OrderCreateTo detail = iOrderService.detail(orderSn);
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
    @GetMapping("/confirm")
    public AjaxResult<OrderCreateTo> confirm() {
        OrderCreateTo orderCreateTo = iOrderService.createOrder();
        return AjaxResult.success(orderCreateTo);
    }

    /**
     * 提交订单
     *
     * @return Object
     */
    @Log(title = "提交订单")
    @GetMapping("/submit")
    public AjaxResult<Object> submit(@RequestBody OrderSubmitVo orderSubmitVo) {
        iOrderService.submitOrder(orderSubmitVo);
        return AjaxResult.success();
    }

    /**
     * 关闭订单
     *
     * @return Object
     */
    @Log(title = "关闭订单")
    @GetMapping("/close")
    public AjaxResult<Object> close(@RequestParam("id") Long id) {
        return AjaxResult.success();
    }

    /**
     * 删除订单
     *
     * @return Object
     */
    @Log(title = "删除订单")
    @GetMapping("/delete")
    public AjaxResult<Object> delete(@RequestParam("id") Long id) {
        return AjaxResult.success();
    }

    /**
     * 完成订单
     *
     * @return Object
     */
    @Log(title = "完成订单")
    @GetMapping("/complete")
    public AjaxResult<Object> complete(@RequestParam("id") Long id) {
        return AjaxResult.success();
    }

    /**
     * 根据订单编号查询订单状态
     * @param orderSn
     * @return
     */
    @GetMapping(value = "/status/{orderSn}")
    public AjaxResult<OrderVo> getOrderStatus(@RequestParam("orderSn") String orderSn) {
        OrderVo order = iOrderService.getOrderByOrderSn(orderSn);
        return AjaxResult.success(order);
    }


    /**
     * 更改备注
     * @param orderParam
     * @return
     */
    @PostMapping(value = "/updateNote")
    public AjaxResult<Object> updateNote(@Validated(value = OrderParam.update.class) @RequestBody OrderParam orderParam) {
        final String orderSn = orderParam.getOrderSn();
        final String note = orderParam.getNote();
        iOrderService.updateNote(orderSn,note);
        return AjaxResult.success();
    }
}
