package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.vo.SeckillSessionWithSkusVo;
import com.mdd.coupon.service.ISeckillSessionService;
import com.mdd.coupon.validate.SeckillSessionParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SeckillSessionListVo;
import com.mdd.coupon.vo.SeckillSessionDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 秒杀活动场次管理
 */
@RestController
@RequestMapping("api/coupon/session")
public class SeckillSessionController {

    @Resource
    ISeckillSessionService iSeckillSessionService;

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
        PageResult<SeckillSessionListVo> list = iSeckillSessionService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀活动场次详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SeckillSessionDetailVo detail = iSeckillSessionService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀活动场次新增
     *
     * @param seckillSessionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动场次新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SeckillSessionParam.create.class) @RequestBody SeckillSessionParam seckillSessionParam) {
        iSeckillSessionService.add(seckillSessionParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动场次编辑
     *
     * @param seckillSessionParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动场次编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SeckillSessionParam.update.class) @RequestBody SeckillSessionParam seckillSessionParam) {
        iSeckillSessionService.edit(seckillSessionParam);
        return AjaxResult.success();
    }


    /**
     * 秒杀活动场次批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "秒杀活动场次批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSeckillSessionService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    /**
     * 查询最近三天需要参加秒杀商品的信息
     * @return
     */
    @Log(title = "查询最近三天需要参加秒杀商品的信息")
    @GetMapping(value = "/lates3DaySession")
    public AjaxResult<List<SeckillSessionWithSkusVo>> getLates3DaySession() {
        List<SeckillSessionWithSkusVo> seckillSessionWithSkusVos = iSeckillSessionService.getLates3DaySession();
        return AjaxResult.success(seckillSessionWithSkusVos);
    }

}
