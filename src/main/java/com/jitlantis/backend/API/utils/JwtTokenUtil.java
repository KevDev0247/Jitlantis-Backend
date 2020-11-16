package com.jitlantis.backend.API.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    public static final String TOKEN_HEADER = "wolf-token";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "wolf";
    private static final String ISS = "jonas";

    private static final String ROLE_CLAIMS = "wolf_role";
    private static final long EXPIRATION = 8640000L;

    public static Map<String, Object> getDefaultTokenUser(String tokenId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "-1");
        map.put("openId", "");
        map.put("username", "Kevin");
        map.put("tokenId", tokenId);
        return map;
    }

    public static String createToken(Map<String, Object> userMap) {
        String subject = JSONObject.toJSONString(userMap);
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, ROLE_CLAIMS);
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET).setClaims(map).setIssuer(ISS)
                .setSubject(subject).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000)).compact();
    }

    public static Map<String, Object> getUserObject(String token) {
        String subject = getTokenBody(token).getSubject();
        return JSONObject.parseObject(subject);
    }

    public static String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    public static boolean isExpired(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException exception) {
            return true;
        }
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userId", "");
        userMap.put("username", "KevinWang");
        userMap.put("tokenId", CommonUtils.getUUID());
        String token = JwtTokenUtil.createToken(userMap);
        System.out.println(token);
    }
}
