package com.mdd.common.validator;

import com.mdd.common.validator.annotation.IDLongMust;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: server
 * @description: 校验主键ID参数Long
 * @author: Claire
 * @create: 2022-11-08 23:20
 **/
public class IDLongMustValidator implements ConstraintValidator<IDLongMust, Long> {

    @Override
    public void initialize(IDLongMust constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value >= 0;
    }

}