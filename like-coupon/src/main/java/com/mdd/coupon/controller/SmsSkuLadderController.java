package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISmsSkuLadderService;
import com.mdd.coupon.validate.SmsSkuLadderParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SmsSkuLadderListVo;
import com.mdd.coupon.vo.SmsSkuLadderDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品阶梯价格管理
 */
@RestController
@RequestMapping("api/ladder")
public class SmsSkuLadderController {

    @Resource
    ISmsSkuLadderService iSmsSkuLadderService;

    /**
     * 商品阶梯价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsSkuLadderListVo> list = iSmsSkuLadderService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品阶梯价格详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsSkuLadderDetailVo detail = iSmsSkuLadderService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品阶梯价格新增
     *
     * @param smsSkuLadderParam 参数
     * @return Object
     */
    @Log(title = "商品阶梯价格新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsSkuLadderParam.create.class) @RequestBody SmsSkuLadderParam smsSkuLadderParam) {
        iSmsSkuLadderService.add(smsSkuLadderParam);
        return AjaxResult.success();
    }

    /**
     * 商品阶梯价格编辑
     *
     * @param smsSkuLadderParam 参数
     * @return Object
     */
    @Log(title = "商品阶梯价格编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsSkuLadderParam.update.class) @RequestBody SmsSkuLadderParam smsSkuLadderParam) {
        iSmsSkuLadderService.edit(smsSkuLadderParam);
        return AjaxResult.success();
    }

    /**
     * 商品阶梯价格删除
     *
     * @param smsSkuLadderParam 参数
     * @return Object
     */
    @Log(title = "商品阶梯价格删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsSkuLadderParam.delete.class) @RequestBody SmsSkuLadderParam smsSkuLadderParam) {
        iSmsSkuLadderService.del(smsSkuLadderParam.getId());
        return AjaxResult.success();
    }

}
