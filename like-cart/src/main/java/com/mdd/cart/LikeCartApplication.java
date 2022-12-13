package com.mdd.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 11:32
 **/
//扫描RedisUtil
@ComponentScan(basePackages = {"com.mdd"})
@EnableFeignClients(basePackages = "com.mdd.cart.feign")
@EnableDiscoveryClient
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class LikeCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeCartApplication.class, args);
    }
}
