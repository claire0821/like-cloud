package com.mdd.member.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.member.service.IUmsMemberReceiveAddressService;
import com.mdd.member.validate.UmsMemberReceiveAddressParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.vo.UmsMemberReceiveAddressListVo;
import com.mdd.member.vo.UmsMemberReceiveAddressDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员收货地址管理
 */
@RestController
@RequestMapping("api/address")
public class UmsMemberReceiveAddressController {

    @Resource
    IUmsMemberReceiveAddressService iUmsMemberReceiveAddressService;

    /**
     * 会员收货地址列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UmsMemberReceiveAddressListVo> list = iUmsMemberReceiveAddressService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员收货地址详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberReceiveAddressDetailVo detail = iUmsMemberReceiveAddressService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员收货地址新增
     *
     * @param umsMemberReceiveAddressParam 参数
     * @return Object
     */
    @Log(title = "会员收货地址新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberReceiveAddressParam.create.class) @RequestBody UmsMemberReceiveAddressParam umsMemberReceiveAddressParam) {
        iUmsMemberReceiveAddressService.add(umsMemberReceiveAddressParam);
        return AjaxResult.success();
    }

    /**
     * 会员收货地址编辑
     *
     * @param umsMemberReceiveAddressParam 参数
     * @return Object
     */
    @Log(title = "会员收货地址编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberReceiveAddressParam.update.class) @RequestBody UmsMemberReceiveAddressParam umsMemberReceiveAddressParam) {
        iUmsMemberReceiveAddressService.edit(umsMemberReceiveAddressParam);
        return AjaxResult.success();
    }

    /**
     * 会员收货地址删除
     *
     * @param umsMemberReceiveAddressParam 参数
     * @return Object
     */
    @Log(title = "会员收货地址删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberReceiveAddressParam.delete.class) @RequestBody UmsMemberReceiveAddressParam umsMemberReceiveAddressParam) {
        iUmsMemberReceiveAddressService.del(umsMemberReceiveAddressParam.getId());
        return AjaxResult.success();
    }

}
