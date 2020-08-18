package com.jitlantis.backend.API.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.service.LoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private LoginService loginService;

    public JWTAuthenticationFilter(LoginService loginService, AuthenticationSuccessHandler successHandler) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationSuccessHandler(successHandler);
        this.loginService = loginService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        SysUser credentials = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
        SysUser sysUserEntity = loginService.login(credentials.getName(), credentials.getPassword());
        if (sysUserEntity != null) {
            request.setAttribute(SecurityConstants.CURRENT_USER_REQ, sysUserEntity);
            return new TokenBasedAuthentication(credentials.getName());
        } throw new AuthenticationException("Authentication failed: " + credentials.getName()) {};
    }
}
