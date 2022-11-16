package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISeckillPromotionService;
import com.mdd.coupon.validate.SeckillPromotionParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SeckillPromotionListVo;
import com.mdd.coupon.vo.SeckillPromotionDetailVo;
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
 * 秒杀活动管理
 */
@RestController
@RequestMapping("api/coupon/promotion")
public class SeckillPromotionController {

    @Resource
    ISeckillPromotionService iSeckillPromotionService;

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
        PageResult<SeckillPromotionListVo> list = iSeckillPromotionService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀活动详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SeckillPromotionDetailVo detail = iSeckillPromotionService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀活动新增
     *
     * @param seckillPromotionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SeckillPromotionParam.create.class) @RequestBody SeckillPromotionParam seckillPromotionParam) {
        iSeckillPromotionService.add(seckillPromotionParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动编辑
     *
     * @param seckillPromotionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SeckillPromotionParam.update.class) @RequestBody SeckillPromotionParam seckillPromotionParam) {
        iSeckillPromotionService.edit(seckillPromotionParam);
        return AjaxResult.success();
    }


    /**
     * 秒杀活动批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "秒杀活动批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSeckillPromotionService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
