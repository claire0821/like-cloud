package com.mdd.seckill.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: server
 * @description: 自定义阻塞返回方法
 * @author: Claire
 * @create: 2023-01-05 17:38
 **/
@Component
public class SentinelUrlBlockHandler implements BlockExceptionHandler {

    /**
     * 自定义限流返回信息
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        // 降级业务处理
        AjaxResult error = AjaxResult.failed(HttpEnum.TO_MANY_REQUEST.getCode(), HttpEnum.TO_MANY_REQUEST.getMsg());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(error));
    }
}
