package com.mdd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 17:03
 **/
//@ComponentScan(basePackages = {"com.mdd.common.utils"})
//@EnableFeignClients(basePackages = {"com.mdd.common.feign"})
@EnableDiscoveryClient
@SpringBootApplication()
public class LikeGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeGatewayApplication.class, args);
    }
}
