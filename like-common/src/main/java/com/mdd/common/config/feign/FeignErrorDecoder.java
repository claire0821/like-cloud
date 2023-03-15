package com.mdd.common.config.feign;

import com.alibaba.fastjson2.JSONObject;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.BaseException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @program: server
 * @description: Fegin返回非200拦截统一处理
 * @author: Claire
 * @create: 2023-03-15 14:15
 **/
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        try {
            if(response.status() != HttpStatus.SC_OK) {
                String resultStr = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
                throw new BaseException(HttpEnum.SYSTEM_ERROR.getCode(),resultStr);
//                AjaxResult ajaxResult = new AjaxResult(HttpEnum.CALL_SERVICE_ERROR,resultStr);
//                response.status()
            } else {

            }
        } catch (IOException ignored) {
        }
        return decode(methodKey, response);
    }
}
