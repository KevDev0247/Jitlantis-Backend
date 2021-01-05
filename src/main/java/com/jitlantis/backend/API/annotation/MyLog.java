package com.jitlantis.backend.API.annotation;

import com.jitlantis.backend.API.enums.BusinessType;
import com.jitlantis.backend.API.model.Accessory;

import java.lang.annotation.*;

/**
 * A customized annotation for logs
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String value() default "";

    public BusinessType businessType() default  BusinessType.OTHER;
}
