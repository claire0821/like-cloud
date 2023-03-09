package com.mdd.common.config.fegin;

import com.alibaba.fastjson2.JSONObject;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.BaseException;
import com.mdd.common.exception.LoginException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-03-03 14:06
 **/
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        throw new LoginException(HttpEnum.SMAE_MOBILE.getCode(), HttpEnum.SMAE_MOBILE.getMsg());
//        try {
//
//            String message = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
//            AjaxResult res = JSONObject.parseObject(message, AjaxResult.class);
//            LoginException loginException = new LoginException(res.getCode(), res.getMsg());
//            throw loginException;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return errorDecoder.decode(methodKey, response);
    }
}
