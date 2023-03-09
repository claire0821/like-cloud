package com.mdd.common.config.fegin;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-03-03 14:05
 **/
//@Configuration
//@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientExceptionErrorDecoder();
    }

}
