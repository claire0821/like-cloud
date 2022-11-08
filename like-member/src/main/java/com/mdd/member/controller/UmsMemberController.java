package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.entity.UmsMember;
import com.mdd.member.feign.CouponFeignService;
import com.mdd.member.service.IUmsMemberService;
import com.mdd.member.validate.UmsMemberParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.UmsMemberListVo;
import com.mdd.member.vo.UmsMemberDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员管理
 */
@RestController
@RequestMapping("api/umsmember")
public class UmsMemberController {

    @Resource
    IUmsMemberService iUmsMemberService;

    @Autowired
    CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public Object test() {
        UmsMember umsMember = new UmsMember();
        umsMember.setNickname("张三");
        final Object membercoupons = couponFeignService.membercoupons();
        return AjaxResult.success(membercoupons);
    }
    /**
     * 会员列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UmsMemberListVo> list = iUmsMemberService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberDetailVo detail = iUmsMemberService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员新增
     *
     * @param umsMemberParam 参数
     * @return Object
     */
    @Log(title = "会员新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberParam.create.class) @RequestBody UmsMemberParam umsMemberParam) {
        iUmsMemberService.add(umsMemberParam);
        return AjaxResult.success();
    }

    /**
     * 会员编辑
     *
     * @param umsMemberParam 参数
     * @return Object
     */
    @Log(title = "会员编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberParam.update.class) @RequestBody UmsMemberParam umsMemberParam) {
        iUmsMemberService.edit(umsMemberParam);
        return AjaxResult.success();
    }

    /**
     * 会员删除
     *
     * @param umsMemberParam 参数
     * @return Object
     */
    @Log(title = "会员删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberParam.delete.class) @RequestBody UmsMemberParam umsMemberParam) {
        iUmsMemberService.del(umsMemberParam.getId());
        return AjaxResult.success();
    }

}
