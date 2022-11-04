package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsSeckillSkuRelationService;
import com.mdd.coupon.validate.SmsSeckillSkuRelationParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsSeckillSkuRelationListVo;
import com.mdd.coupon.vo.SmsSeckillSkuRelationDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 秒杀活动商品关联管理
 */
@RestController
@RequestMapping("api/skurelation")
public class SmsSeckillSkuRelationController {

    @Resource
    ISmsSeckillSkuRelationService iSmsSeckillSkuRelationService;

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
        PageResult<SmsSeckillSkuRelationListVo> list = iSmsSeckillSkuRelationService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 秒杀活动商品关联详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSeckillSkuRelationDetailVo detail = iSmsSeckillSkuRelationService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 秒杀活动商品关联新增
     *
     * @param smsSeckillSkuRelationParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动商品关联新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSeckillSkuRelationParam.create.class) @RequestBody SmsSeckillSkuRelationParam smsSeckillSkuRelationParam) {
        iSmsSeckillSkuRelationService.add(smsSeckillSkuRelationParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动商品关联编辑
     *
     * @param smsSeckillSkuRelationParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动商品关联编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSeckillSkuRelationParam.update.class) @RequestBody SmsSeckillSkuRelationParam smsSeckillSkuRelationParam) {
        iSmsSeckillSkuRelationService.edit(smsSeckillSkuRelationParam);
        return AjaxResult.success();
    }

    /**
     * 秒杀活动商品关联删除
     *
     * @param smsSeckillSkuRelationParam 参数
     * @return Object
     */
    @Log(title = "秒杀活动商品关联删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSeckillSkuRelationParam.delete.class) @RequestBody SmsSeckillSkuRelationParam smsSeckillSkuRelationParam) {
        iSmsSeckillSkuRelationService.del(smsSeckillSkuRelationParam.getId());
        return AjaxResult.success();
    }

}
