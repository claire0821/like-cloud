package com.mdd.common.validator.annotation;

import com.mdd.common.validator.IDLongMustValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @program: server
 * @description: 校验主键ID参数Long
 * @author: Claire
 * @create: 2022-11-08 23:21
 **/
@Documented
@Constraint(validatedBy = IDLongMustValidator.class)
@Target({ ElementType.PARAMETER,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IDLongMust {

    String message() default "主键参数必须存在且大于等于0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

}