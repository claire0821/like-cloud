package com.mdd.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-05 11:54
 **/
@ConfigurationProperties(prefix = "like.thread")
//@Component
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;


}