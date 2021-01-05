package com.jitlantis.backend.API.annotation;

import com.jitlantis.backend.API.model.SysUserRole;
import com.jitlantis.backend.API.service.SysUserRoleService;
import com.jitlantis.backend.API.utils.StringUtils;
import com.jitlantis.backend.API.utils.TokenUtil;
import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Order(-10)
public aspect AuthenticationAspect {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut(value = "@annotation(authentication)", argNames = "authentication")
    public void AuthenticationService(Authentication authentication) {
    }

    @Around(value = "AuthenticationService(authentication)", argNames = "joinPoint,authentication")
    public Object doAround(ProceedingJoinPoint joinPoint, Authentication authentication) throws Throwable {
        beginTime.set(System.currentTimeMillis());
        String userId = null;
        List<Integer> roleList = new ArrayList<>();

        for (Object arg : joinPoint.getArgs()) {
            if (arg != null && arg.getClass() == RequestFacade.class) {
                userId = TokenUtil.getTokenUserId();
                if (StringUtils.isNotBlank(userId)) {
                    return "Verification failed";
                }

                List<SysUserRole> roles = sysUserRoleService.getRolesByUserName(userId);
                for (SysUserRole userRole: roles) {
                    roleList.add(userRole.getRoleId());
                }
                break;
            }
        }

        long[] aims = authentication.role();
        boolean isPass = false;
        for (long aim : aims) {
            if (roleList.contains(aim)) {
                isPass = true;
                break;
            }
        }

        if (isPass) {
            return joinPoint.proceed();
        } else {
            beginTime.set(System.currentTimeMillis());
            return "Verification failed";
        }
    }
}
