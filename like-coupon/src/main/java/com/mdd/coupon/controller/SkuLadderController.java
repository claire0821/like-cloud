package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.ISkuLadderService;
import com.mdd.coupon.validate.SkuLadderParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.SkuLadderListVo;
import com.mdd.coupon.vo.SkuLadderDetailVo;
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
 * 商品阶梯价格管理
 */
@RestController
@RequestMapping("api/coupon/ladder")
public class SkuLadderController {

    @Resource
    ISkuLadderService iSkuLadderService;

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
        PageResult<SkuLadderListVo> list = iSkuLadderService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品阶梯价格详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SkuLadderDetailVo detail = iSkuLadderService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品阶梯价格新增
     *
     * @param skuLadderParam 参数
     * @return Object
     */
    @Log(title = "商品阶梯价格新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SkuLadderParam.create.class) @RequestBody SkuLadderParam skuLadderParam) {
        iSkuLadderService.add(skuLadderParam);
        return AjaxResult.success();
    }

    /**
     * 商品阶梯价格编辑
     *
     * @param skuLadderParam 参数
     * @return Object
     */
    @Log(title = "商品阶梯价格编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SkuLadderParam.update.class) @RequestBody SkuLadderParam skuLadderParam) {
        iSkuLadderService.edit(skuLadderParam);
        return AjaxResult.success();
    }


    /**
     * 商品阶梯价格批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品阶梯价格批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSkuLadderService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
