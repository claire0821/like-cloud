package com.mdd.common.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.vo.CartItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("like-admin")
public interface ISystemConfigFeignService {
    /**
     * 获取系统全局配置列表
     * @return
     */
    @GetMapping("/api/system/config/list")
    AjaxResult<List<SystemConfig>> list(@RequestParam(value = "type",required = false) String type,
                                        @RequestParam(value = "name",required = false) String name);
    /**
     * 获取系统全局配置
     * @return
     */
    @GetMapping("/api/system/config/one")
    AjaxResult<SystemConfig> one(@RequestParam(value = "type",required = false) String type,
                                 @RequestParam(value = "name",required = false) String name);

    @PostMapping("/updateById")
    AjaxResult<Object> updateById(@RequestBody SystemConfig SystemConfig);

    @PostMapping("/insert")
    AjaxResult<Object> insert(@RequestBody SystemConfig SystemConfig);
}
