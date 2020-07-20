package com.phiotonia.kniotcloud.backend.security;

public class UserTokenState {

    private String token;
    private Long expiresIn;

    public UserTokenState(String accessToken, long expiresIn) {
        this.token = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }
}
