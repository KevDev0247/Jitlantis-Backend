package com.jitlantis.backend.API.annotation;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.jitlantis.backend.API.annotation.MyLog)")
    public void logPointCut() { }

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);

        if (myLog != null) {
            String value = myLog.value();
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();

        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

        }
    }
}
