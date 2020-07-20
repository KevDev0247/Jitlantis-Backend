package com.phiotonia.kniotcloud.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.phiotonia.kniotcloud.backend.security.SecurityConstants.EXPIRATION_TIME;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        String username = (String) authentication.getPrincipal();

        String jws = tokenHelper.generateToken(username);
        UserTokenState userTokenState = new UserTokenState(jws, EXPIRATION_TIME);
        String jwtResponse = objectMapper.writeValueAsString(userTokenState);

        response.setContentType("application/json");
        response.getWriter().write(jwtResponse);
    }
}
