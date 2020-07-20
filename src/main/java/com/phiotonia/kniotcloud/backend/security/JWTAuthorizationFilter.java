package com.phiotonia.kniotcloud.backend.security;

import com.phiotonia.kniotcloud.backend.model.User;
import com.phiotonia.kniotcloud.backend.service.LoginService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.phiotonia.kniotcloud.backend.security.SecurityConstants.CURRENT_USER_REQ;

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
            User userEntity = loginService.loadUserById(userId);
            if (userEntity != null) {
                request.setAttribute(CURRENT_USER_REQ, userEntity);
                SecurityContextHolder.getContext().setAuthentication(new TokenBasedAuthentication(userId));
            }
        }
        filterChain.doFilter(request, response);
    }
}
