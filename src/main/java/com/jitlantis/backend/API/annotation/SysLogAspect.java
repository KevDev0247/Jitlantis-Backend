package com.jitlantis.backend.API.annotation;

import com.alibaba.fastjson.JSON;
import com.jitlantis.backend.API.model.SysLog;
import com.jitlantis.backend.API.service.SysLogService;
import com.jitlantis.backend.API.utils.HttpContextUtils;
import com.jitlantis.backend.API.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.jitlantis.backend.API.annotation.MyLog)")
    public void logPointCut() { }

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);

        if (myLog != null) {
            String value = myLog.value();
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        sysLog.setCreateTime(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            sysLog.setUserName(authentication.getName());
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IpUtils.getIpAddress(request));

        sysLogService.insert(sysLog);
    }
}
