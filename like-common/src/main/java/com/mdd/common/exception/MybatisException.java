package com.mdd.common.exception;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

/**
 * @program: server
 * @description: 全局MybatisPlus异常处理
 * @author: Claire
 * @create: 2023-03-15 15:38
 **/
@Slf4j
@ControllerAdvice
@ConditionalOnClass(com.baomidou.mybatisplus.core.exceptions.MybatisPlusException.class)
public class MybatisException {
    /**
     * 拦截MybatisPlus异常
     */
//    @ConditionalOnClass(name = "com.baomidou.mybatisplus.core.exceptions.MybatisPlusException")
//    @ConditionalOnClass({com.baomidou.mybatisplus.core.exceptions.MybatisPlusException.class})
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(com.baomidou.mybatisplus.core.exceptions.MybatisPlusException.class)
    @ResponseBody
    public AjaxResult handleMybatisPlusException(com.baomidou.mybatisplus.core.exceptions.MybatisPlusException e) {
        Integer code = HttpEnum.ASSERT_MYBATIS_ERROR.getCode();
        String msg   = Objects.requireNonNull(e.getMessage());
        return AjaxResult.failed(code, msg);
    }
}
