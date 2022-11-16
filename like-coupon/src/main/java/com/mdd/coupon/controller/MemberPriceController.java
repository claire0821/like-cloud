package com.mdd.coupon.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.coupon.service.IMemberPriceService;
import com.mdd.coupon.validate.MemberPriceParam;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.vo.MemberPriceListVo;
import com.mdd.coupon.vo.MemberPriceDetailVo;
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
 * 商品会员价格管理
 */
@RestController
@RequestMapping("api/coupon/price")
public class MemberPriceController {

    @Resource
    IMemberPriceService iMemberPriceService;

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
        PageResult<MemberPriceListVo> list = iMemberPriceService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品会员价格详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberPriceDetailVo detail = iMemberPriceService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品会员价格新增
     *
     * @param memberPriceParam 参数
     * @return Object
     */
    @Log(title = "商品会员价格新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberPriceParam.create.class) @RequestBody MemberPriceParam memberPriceParam) {
        iMemberPriceService.add(memberPriceParam);
        return AjaxResult.success();
    }

    /**
     * 商品会员价格编辑
     *
     * @param memberPriceParam 参数
     * @return Object
     */
    @Log(title = "商品会员价格编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberPriceParam.update.class) @RequestBody MemberPriceParam memberPriceParam) {
        iMemberPriceService.edit(memberPriceParam);
        return AjaxResult.success();
    }


    /**
     * 商品会员价格批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品会员价格批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberPriceService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
