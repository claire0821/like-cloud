package com.mdd.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 11:32
 **/

@EnableFeignClients(basePackages = "com.mdd.ware.feign")
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.mdd"})
@MapperScan(basePackages = {"com.mdd.*.mapper"})
@SpringBootApplication()
public class LikeWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeWareApplication.class, args);
    }
}
