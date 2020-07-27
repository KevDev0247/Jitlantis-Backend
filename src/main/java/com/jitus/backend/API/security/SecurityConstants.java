package com.jitus.backend.API.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000;
    public static final String SECRETE = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/sign-up";
    public static final String CURRENT_USER_REQ = "__current_user__";
}
