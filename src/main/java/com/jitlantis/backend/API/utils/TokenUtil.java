package com.jitlantis.backend.API.utils;

import com.jitlantis.backend.API.security.TokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.jitlantis.backend.API.security.SecurityConstants.SECRET;
import static com.jitlantis.backend.API.security.SecurityConstants.TOKEN_PREFIX;

public class TokenUtil {

    public static String getTokenUserId() {
        String authHeader = getRequest().getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            authHeader = authHeader.replace(TOKEN_PREFIX, "");
        } else {
            return null;
        }

        String userId = new TokenHelper().getUsernameFromToken(authHeader);
        return userId;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    public static Map<String, Object> getUserObject(String token) {
        String subject = getTokenBody(token).getSubject();

        return JSONObject.parseObject(subject);
    }

    public static String getUserName(String token) {
        String subject = getTokenBody(token).getSubject();
        JSONObject jsonObject = JSONObject.parseObject(subject);

        return subject;
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}
