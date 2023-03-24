package com.mdd.common.config.feign;

import com.alibaba.fastjson2.JSONObject;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.BaseException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @program: server
 * @description: Fegin返回200拦截统一处理
 * @author: Claire
 * @create: 2023-03-03 11:46
 **/
@Component
public class FeginResponseEntityDecoder implements Decoder, SmartInitializingSingleton {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    private ResponseEntityDecoder responseEntityDecoder;

    @Override
    public void afterSingletonsInstantiated() {
        responseEntityDecoder = new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        String resultStr = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        AjaxResult res = JSONObject.parseObject(resultStr, AjaxResult.class);
        if(res.getCode() == HttpEnum.SUCCESS.getCode()) {
            return responseEntityDecoder.decode(response.toBuilder().body(resultStr, StandardCharsets.UTF_8).build(), type);
        } else {
            throw new BaseException(res.getCode(), res.getMsg());
        }
    }
}
