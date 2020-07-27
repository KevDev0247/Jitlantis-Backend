package com.jitus.backend.API.security;

import com.jitus.backend.API.model.SysUser;
import com.jitus.backend.API.service.LoginService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private TokenHelper tokenHelper;
    private LoginService loginService;

    public JWTAuthorizationFilter(TokenHelper tokenHelper, LoginService loginService) {
        this.tokenHelper = tokenHelper;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = tokenHelper.getToken(request);
        if (token != null) {
            String userId = tokenHelper.getUsernameFromToken(token);
            SysUser sysUserEntity = loginService.loadUserById(userId);
            if (sysUserEntity != null) {
                request.setAttribute(SecurityConstants.CURRENT_USER_REQ, sysUserEntity);
                SecurityContextHolder.getContext().setAuthentication(new TokenBasedAuthentication(userId));
            }
        }
        filterChain.doFilter(request, response);
    }
}
