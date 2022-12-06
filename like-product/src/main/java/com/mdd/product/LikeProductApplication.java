package com.mdd.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-11-02 11:32
 **/

@EnableFeignClients(basePackages = "com.mdd.product.feign")
@EnableDiscoveryClient
@Configuration
@ComponentScan(basePackages = {"com.mdd"})
//@ComponentScan(basePackages = {"com.mdd.common.config"})
@MapperScan(basePackages = {"com.mdd.*.mapper"})
@EnableTransactionManagement
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class LikeProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeProductApplication.class, args);
    }
}
