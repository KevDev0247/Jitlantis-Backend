package com.jitlantis.backend.API.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A customized annotation for authentication
 *
 * @author Kevin Zhijun Wang
 * created on 2021/01/04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
    long[] role() default {};
    long[] menu() default {};
}
