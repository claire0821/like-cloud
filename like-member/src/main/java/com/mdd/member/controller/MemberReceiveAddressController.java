package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.vo.MemberReceiveAddressVo;
import com.mdd.member.service.IMemberReceiveAddressService;
import com.mdd.member.validate.MemberReceiveAddressParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.MemberReceiveAddressListVo;
import com.mdd.member.vo.MemberReceiveAddressDetailVo;
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
 * 会员收货地址管理
 */
@RestController
@RequestMapping("api/member/address")
public class MemberReceiveAddressController {

    @Resource
    IMemberReceiveAddressService iMemberReceiveAddressService;

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
        PageResult<MemberReceiveAddressListVo> list = iMemberReceiveAddressService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员收货地址详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberReceiveAddressDetailVo detail = iMemberReceiveAddressService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员收货地址新增
     *
     * @param memberReceiveAddressParam 参数
     * @return Object
     */
    @Log(title = "会员收货地址新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberReceiveAddressParam.create.class) @RequestBody MemberReceiveAddressParam memberReceiveAddressParam) {
        iMemberReceiveAddressService.add(memberReceiveAddressParam);
        return AjaxResult.success();
    }

    /**
     * 会员收货地址编辑
     *
     * @param memberReceiveAddressParam 参数
     * @return Object
     */
    @Log(title = "会员收货地址编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberReceiveAddressParam.update.class) @RequestBody MemberReceiveAddressParam memberReceiveAddressParam) {
        iMemberReceiveAddressService.edit(memberReceiveAddressParam);
        return AjaxResult.success();
    }


    /**
     * 会员收货地址批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员收货地址批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberReceiveAddressService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }


    /**
     * 会员收货地址列表
     *
     * @param memberId 用户id
     * @return Object
     */
    @GetMapping("/listByMember")
    public AjaxResult listByMember(@RequestParam("memberId") Long memberId) {
        List<MemberReceiveAddressVo> list = iMemberReceiveAddressService.listByMember(memberId);
        return AjaxResult.success(list);
    }

    /**
     * 会员默认收货地址
     *
     * @param memberId 用户id
     * @return Object
     */
    @GetMapping("/getDefaultAddress")
    public AjaxResult getDefaultAddress(@RequestParam("memberId") Long memberId) {
        MemberReceiveAddressVo vo = iMemberReceiveAddressService.getDefaultAddress(memberId);
        return AjaxResult.success(vo);
    }
}
