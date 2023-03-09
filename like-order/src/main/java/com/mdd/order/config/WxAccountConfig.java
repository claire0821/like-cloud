package com.mdd.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-09 16:26
 **/
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxAccountConfig {

    private String appId;

    private String mchId;

    private String mchKey;

    private String notifyUrl;

    private String returnUrl;

}