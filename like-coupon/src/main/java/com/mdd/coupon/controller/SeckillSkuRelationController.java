package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISeckillSkuRelationService;
import com.mdd.coupon.validate.SeckillSkuRelationParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SeckillSkuRelationListVo;
import com.mdd.coupon.vo.SeckillSkuRelationDetailVo;
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
 * 秒杀活动商品关联管理
 */
@RestController
@RequestMapping("api/coupon/relation")
public class SeckillSkuRelationController {

    @Resource
    ISeckillSkuRelationService iSeckillSkuRelationService;

    /**
     * 秒杀活动商品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SeckillSkuRelationListVo> list = iSeckillSkuRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀活动商品关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SeckillSkuRelationDetailVo detail = iSeckillSkuRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀活动商品关联新增
     *
     * @param seckillSkuRelationParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动商品关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SeckillSkuRelationParam.create.class) @RequestBody SeckillSkuRelationParam seckillSkuRelationParam) {
        iSeckillSkuRelationService.add(seckillSkuRelationParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动商品关联编辑
     *
     * @param seckillSkuRelationParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动商品关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SeckillSkuRelationParam.update.class) @RequestBody SeckillSkuRelationParam seckillSkuRelationParam) {
        iSeckillSkuRelationService.edit(seckillSkuRelationParam);
        return AjaxResult.success();
    }


    /**
     * 秒杀活动商品关联批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "秒杀活动商品关联批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSeckillSkuRelationService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
