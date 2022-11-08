package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsSeckillPromotionService;
import com.mdd.coupon.validate.SmsSeckillPromotionParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsSeckillPromotionListVo;
import com.mdd.coupon.vo.SmsSeckillPromotionDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 秒杀活动管理
 */
@RestController
@RequestMapping("api/promotion")
public class SmsSeckillPromotionController {

    @Resource
    ISmsSeckillPromotionService iSmsSeckillPromotionService;

    /**
     * 秒杀活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsSeckillPromotionListVo> list = iSmsSeckillPromotionService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀活动详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSeckillPromotionDetailVo detail = iSmsSeckillPromotionService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀活动新增
     *
     * @param smsSeckillPromotionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSeckillPromotionParam.create.class) @RequestBody SmsSeckillPromotionParam smsSeckillPromotionParam) {
        iSmsSeckillPromotionService.add(smsSeckillPromotionParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动编辑
     *
     * @param smsSeckillPromotionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSeckillPromotionParam.update.class) @RequestBody SmsSeckillPromotionParam smsSeckillPromotionParam) {
        iSmsSeckillPromotionService.edit(smsSeckillPromotionParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动删除
     *
     * @param smsSeckillPromotionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSeckillPromotionParam.delete.class) @RequestBody SmsSeckillPromotionParam smsSeckillPromotionParam) {
        iSmsSeckillPromotionService.del(smsSeckillPromotionParam.getId());
        return AjaxResult.success();
    }

}
