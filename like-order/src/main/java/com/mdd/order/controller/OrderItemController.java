package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IOrderItemService;
import com.mdd.order.validate.OrderItemParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.OrderItemListVo;
import com.mdd.order.vo.OrderItemDetailVo;
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
 * 订单项信息管理
 */
@RestController
@RequestMapping("api/order/item")
public class OrderItemController {

    @Resource
    IOrderItemService iOrderItemService;

    /**
     * 订单项信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<OrderItemListVo> list = iOrderItemService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 订单项信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        OrderItemDetailVo detail = iOrderItemService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 订单项信息新增
     *
     * @param orderItemParam 参数
     * @return Object
     */
    @Log(title = "订单项信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = OrderItemParam.create.class) @RequestBody OrderItemParam orderItemParam) {
        iOrderItemService.add(orderItemParam);
        return AjaxResult.success();
    }

    /**
     * 订单项信息编辑
     *
     * @param orderItemParam 参数
     * @return Object
     */
    @Log(title = "订单项信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = OrderItemParam.update.class) @RequestBody OrderItemParam orderItemParam) {
        iOrderItemService.edit(orderItemParam);
        return AjaxResult.success();
    }


    /**
     * 订单项信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "订单项信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iOrderItemService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
