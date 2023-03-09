package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IOrderOperateHistoryService;
import com.mdd.order.validate.OrderOperateHistoryParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.OrderOperateHistoryVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 订单操作历史记录管理
 */
@RestController
@RequestMapping("api/order/history")
public class OrderOperateHistoryController {

    @Resource
    IOrderOperateHistoryService iOrderOperateHistoryService;

    /**
     * 订单操作历史记录列表
     *
     * @param orderSn 订单编号
     * @return Object
     */
    @GetMapping("/listByOrder")
    public AjaxResult<List<OrderOperateHistoryVo>> listByOrder(@RequestParam("orderSn") Long orderSn) {
        List<OrderOperateHistoryVo> list = iOrderOperateHistoryService.listByOrder(orderSn);
        return AjaxResult.success(list);
    }
    /**
     * 订单操作历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<OrderOperateHistoryVo>> list(@Validated PageParam pageParam,
                                                              @RequestParam Map<String, String> params) {
        PageResult<OrderOperateHistoryVo> list = iOrderOperateHistoryService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 订单操作历史记录详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        OrderOperateHistoryVo detail = iOrderOperateHistoryService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 订单操作历史记录新增
     *
     * @param orderOperateHistoryParam 参数
     * @return Object
     */
    @Log(title = "订单操作历史记录新增")
    @PostMapping("/add")
    public Object add(@Validated(value = OrderOperateHistoryParam.create.class) @RequestBody OrderOperateHistoryParam orderOperateHistoryParam) {
        iOrderOperateHistoryService.add(orderOperateHistoryParam);
        return AjaxResult.success();
    }

    /**
     * 订单操作历史记录编辑
     *
     * @param orderOperateHistoryParam 参数
     * @return Object
     */
    @Log(title = "订单操作历史记录编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = OrderOperateHistoryParam.update.class) @RequestBody OrderOperateHistoryParam orderOperateHistoryParam) {
        iOrderOperateHistoryService.edit(orderOperateHistoryParam);
        return AjaxResult.success();
    }


    /**
     * 订单操作历史记录批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "订单操作历史记录批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iOrderOperateHistoryService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
