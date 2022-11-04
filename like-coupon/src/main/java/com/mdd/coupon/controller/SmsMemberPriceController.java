package com.mdd.coupon.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.coupon.service.ISmsMemberPriceService;
import com.mdd.coupon.validate.SmsMemberPriceParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.vo.SmsMemberPriceListVo;
import com.mdd.coupon.vo.SmsMemberPriceDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品会员价格管理
 */
@RestController
@RequestMapping("api/price")
public class SmsMemberPriceController {

    @Resource
    ISmsMemberPriceService iSmsMemberPriceService;

    /**
     * 商品会员价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SmsMemberPriceListVo> list = iSmsMemberPriceService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品会员价格详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        SmsMemberPriceDetailVo detail = iSmsMemberPriceService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品会员价格新增
     *
     * @param smsMemberPriceParam 参数
     * @return Object
     */
    @Log(title = "商品会员价格新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SmsMemberPriceParam.create.class) @RequestBody SmsMemberPriceParam smsMemberPriceParam) {
        iSmsMemberPriceService.add(smsMemberPriceParam);
        return AjaxResult.success();
    }

    /**
     * 商品会员价格编辑
     *
     * @param smsMemberPriceParam 参数
     * @return Object
     */
    @Log(title = "商品会员价格编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SmsMemberPriceParam.update.class) @RequestBody SmsMemberPriceParam smsMemberPriceParam) {
        iSmsMemberPriceService.edit(smsMemberPriceParam);
        return AjaxResult.success();
    }

    /**
     * 商品会员价格删除
     *
     * @param smsMemberPriceParam 参数
     * @return Object
     */
    @Log(title = "商品会员价格删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SmsMemberPriceParam.delete.class) @RequestBody SmsMemberPriceParam smsMemberPriceParam) {
        iSmsMemberPriceService.del(smsMemberPriceParam.getId());
        return AjaxResult.success();
    }

}
