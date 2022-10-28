package com.mdd.admin.controller;

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
    @Value("${config.info}")
    private String info;

    @RequestMapping("/get")
    public String get() {
        return info;
    }
}
