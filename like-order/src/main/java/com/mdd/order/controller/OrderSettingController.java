package com.mdd.order.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.order.service.IOrderSettingService;
import com.mdd.order.validate.OrderSettingParam;
import com.mdd.common.validate.PageParam;
import com.mdd.order.vo.OrderSettingListVo;
import com.mdd.order.vo.OrderSettingDetailVo;
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
 * 订单配置信息管理
 */
@RestController
@RequestMapping("api/order/setting")
public class OrderSettingController {

    @Resource
    IOrderSettingService iOrderSettingService;

    /**
     * 订单配置信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<OrderSettingListVo> list = iOrderSettingService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 订单配置信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        OrderSettingDetailVo detail = iOrderSettingService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 订单配置信息新增
     *
     * @param orderSettingParam 参数
     * @return Object
     */
    @Log(title = "订单配置信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = OrderSettingParam.create.class) @RequestBody OrderSettingParam orderSettingParam) {
        iOrderSettingService.add(orderSettingParam);
        return AjaxResult.success();
    }

    /**
     * 订单配置信息编辑
     *
     * @param orderSettingParam 参数
     * @return Object
     */
    @Log(title = "订单配置信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = OrderSettingParam.update.class) @RequestBody OrderSettingParam orderSettingParam) {
        iOrderSettingService.edit(orderSettingParam);
        return AjaxResult.success();
    }


    /**
     * 订单配置信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "订单配置信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iOrderSettingService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
