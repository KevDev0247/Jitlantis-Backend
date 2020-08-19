package com.jitlantis.backend.API.annotation;

import com.jitlantis.backend.API.enums.BusinessType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String value() default "";

    public BusinessType businessType() default  BusinessType.OTHER;
}
