package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsSeckillSessionService;
import com.mdd.coupon.validate.SmsSeckillSessionParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsSeckillSessionListVo;
import com.mdd.coupon.vo.SmsSeckillSessionDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 秒杀活动场次管理
 */
@RestController
@RequestMapping("api/session")
public class SmsSeckillSessionController {

    @Resource
    ISmsSeckillSessionService iSmsSeckillSessionService;

    /**
     * 秒杀活动场次列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsSeckillSessionListVo> list = iSmsSeckillSessionService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀活动场次详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSeckillSessionDetailVo detail = iSmsSeckillSessionService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀活动场次新增
     *
     * @param smsSeckillSessionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动场次新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSeckillSessionParam.create.class) @RequestBody SmsSeckillSessionParam smsSeckillSessionParam) {
        iSmsSeckillSessionService.add(smsSeckillSessionParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动场次编辑
     *
     * @param smsSeckillSessionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动场次编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSeckillSessionParam.update.class) @RequestBody SmsSeckillSessionParam smsSeckillSessionParam) {
        iSmsSeckillSessionService.edit(smsSeckillSessionParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动场次删除
     *
     * @param smsSeckillSessionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动场次删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSeckillSessionParam.delete.class) @RequestBody SmsSeckillSessionParam smsSeckillSessionParam) {
        iSmsSeckillSessionService.del(smsSeckillSessionParam.getId());
        return AjaxResult.success();
    }

}
