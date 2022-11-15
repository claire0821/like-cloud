package com.mdd.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 11:32
 **/

@EnableFeignClients(basePackages = "com.mdd.member.feign")
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.mdd"})
@MapperScan(basePackages = {"com.mdd.*.mapper"})
@SpringBootApplication()
public class LikeMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeMemberApplication.class, args);
    }
}
