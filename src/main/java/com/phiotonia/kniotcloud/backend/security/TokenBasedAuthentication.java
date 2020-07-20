package com.phiotonia.kniotcloud.backend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private String token;
    private final String principle;

    public TokenBasedAuthentication(String principle) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.principle = principle;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
