package com.mdd.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
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
@EnableRabbit
@EnableFeignClients(basePackages = {"com.mdd.order.feign","com.mdd.common.feign"})
@EnableDiscoveryClient
@Configuration
@ComponentScan(basePackages = {"com.mdd"})
@MapperScan(basePackages = {"com.mdd.*.mapper"})
@EnableTransactionManagement
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class LikeOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeOrderApplication.class, args);
    }
}
