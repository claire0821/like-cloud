package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsSeckillSkuNoticeService;
import com.mdd.coupon.validate.SmsSeckillSkuNoticeParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsSeckillSkuNoticeListVo;
import com.mdd.coupon.vo.SmsSeckillSkuNoticeDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 秒杀商品通知订阅管理
 */
@RestController
@RequestMapping("api/notice")
public class SmsSeckillSkuNoticeController {

    @Resource
    ISmsSeckillSkuNoticeService iSmsSeckillSkuNoticeService;

    /**
     * 秒杀商品通知订阅列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsSeckillSkuNoticeListVo> list = iSmsSeckillSkuNoticeService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀商品通知订阅详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSeckillSkuNoticeDetailVo detail = iSmsSeckillSkuNoticeService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀商品通知订阅新增
     *
     * @param smsSeckillSkuNoticeParam 参数
     * @return Object
     */
    @Log(title = "秒杀商品通知订阅新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSeckillSkuNoticeParam.create.class) @RequestBody SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam) {
        iSmsSeckillSkuNoticeService.add(smsSeckillSkuNoticeParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀商品通知订阅编辑
     *
     * @param smsSeckillSkuNoticeParam 参数
     * @return Object
     */
    @Log(title = "秒杀商品通知订阅编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSeckillSkuNoticeParam.update.class) @RequestBody SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam) {
        iSmsSeckillSkuNoticeService.edit(smsSeckillSkuNoticeParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀商品通知订阅删除
     *
     * @param smsSeckillSkuNoticeParam 参数
     * @return Object
     */
    @Log(title = "秒杀商品通知订阅删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSeckillSkuNoticeParam.delete.class) @RequestBody SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam) {
        iSmsSeckillSkuNoticeService.del(smsSeckillSkuNoticeParam.getId());
        return AjaxResult.success();
    }

}
