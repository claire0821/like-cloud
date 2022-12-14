package com.mdd.order.feign;


import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.MemberReceiveAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("like-member")
public interface IMemberFeignService {
    /**
     * 查询当前用户的全部收货地址
     * @param memberId
     * @return
     */
    @GetMapping(value = "/api/member/address/getAddress")
    AjaxResult getAddress(@RequestParam("memberId") Long memberId);

    /**
     * 查询当前用户的默认收货地址
     * @param memberId
     * @return
     */
    @GetMapping(value = "/api/member/address/getDefaultAddress")
    AjaxResult getDefaultAddress(@RequestParam("memberId") Long memberId);
}
