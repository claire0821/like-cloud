package com.mdd.order.feign;


import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.MemberReceiveAddressVo;
import com.mdd.common.vo.MemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("like-member")
public interface IMemberFeignService {
    /**
     * 查询当前用户的全部收货地址
     * @return
     */
    @GetMapping(value = "/api/member/address/listByMember")
    AjaxResult<List<MemberReceiveAddressVo>> listByMember();

    /**
     * 查询当前用户的默认收货地址
     * @return
     */
    @GetMapping(value = "/api/member/address/getDefaultAddress")
    AjaxResult<MemberReceiveAddressVo> getDefaultAddress();

    /**
     * 查询当前用户的默认收货地址
     * @return
     */
    @GetMapping(value = "/api/member/address/detail")
    AjaxResult<MemberReceiveAddressVo> getAddress(@RequestParam("id") Long id);

    /**
     * 查询会员详情
     * @return
     */
    @GetMapping(value = "/api/member/member/detail")
    AjaxResult<MemberVo> detail();

    /**
     * 查询会员详情
     * @return
     */
    @GetMapping(value = "/api/member/member/detailById")
    AjaxResult<MemberVo> detailById(@RequestParam("id") Long id);
}
