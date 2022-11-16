package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISeckillSkuNoticeService;
import com.mdd.coupon.validate.SeckillSkuNoticeParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SeckillSkuNoticeListVo;
import com.mdd.coupon.vo.SeckillSkuNoticeDetailVo;
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
 * 秒杀商品通知订阅管理
 */
@RestController
@RequestMapping("api/coupon/notice")
public class SeckillSkuNoticeController {

    @Resource
    ISeckillSkuNoticeService iSeckillSkuNoticeService;

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
        PageResult<SeckillSkuNoticeListVo> list = iSeckillSkuNoticeService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀商品通知订阅详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SeckillSkuNoticeDetailVo detail = iSeckillSkuNoticeService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀商品通知订阅新增
     *
     * @param seckillSkuNoticeParam 参数
     * @return Object
     */
    @Log(title = "秒杀商品通知订阅新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SeckillSkuNoticeParam.create.class) @RequestBody SeckillSkuNoticeParam seckillSkuNoticeParam) {
        iSeckillSkuNoticeService.add(seckillSkuNoticeParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀商品通知订阅编辑
     *
     * @param seckillSkuNoticeParam 参数
     * @return Object
     */
    @Log(title = "秒杀商品通知订阅编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SeckillSkuNoticeParam.update.class) @RequestBody SeckillSkuNoticeParam seckillSkuNoticeParam) {
        iSeckillSkuNoticeService.edit(seckillSkuNoticeParam);
        return AjaxResult.success();
    }


    /**
     * 秒杀商品通知订阅批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "秒杀商品通知订阅批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSeckillSkuNoticeService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
