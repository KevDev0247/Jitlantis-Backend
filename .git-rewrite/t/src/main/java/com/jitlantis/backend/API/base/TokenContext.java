package com.jitlantis.backend.API.base;

import com.jitlantis.backend.API.utils.JwtTokenUtil;
import com.jitlantis.backend.API.utils.TokenUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static com.jitlantis.backend.API.security.SecurityConstants.HEADER_STRING;

public class TokenContext {

    public String getContextUser () {
        Object tokenObject = RequestContextHolder.getRequestAttributes().getAttribute(HEADER_STRING, RequestAttributes.SCOPE_REQUEST);
        if (tokenObject != null) {
            String token = String.valueOf(tokenObject);
            if (token != null) {
                return TokenUtil.getUserName(token);
            }
        }

        return null;
    }
}
