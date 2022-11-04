package com.mdd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 17:03
 **/
@EnableDiscoveryClient
@SpringBootApplication()
public class LikeGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeGatewayApplication.class, args);
    }
}
