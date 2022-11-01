package com.mdd.admin.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-10-28 13:24
 **/
@RestController
@RequestMapping("/api/config")
@RefreshScope
public class ConfigController {
    @Value("${spring.datasource.url}")
    private String info;

    @SentinelResource(value = "get", blockHandler = "handleException")
    @RequestMapping("/get")
    public String get() {
        return info;
    }

    public String handleException(BlockException exception) {
        return "error";
    }
}
