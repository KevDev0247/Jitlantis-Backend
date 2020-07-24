package com.phiotonia.kniotcloud.backend.security;

import com.phiotonia.kniotcloud.backend.service.LoginService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.phiotonia.kniotcloud.backend.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private TokenHelper tokenHelper;
    private CORSFilter corsFilter;
    private LoginService loginService;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public WebSecurityConfig(UserDetailsService userDetailsService, TokenHelper tokenHelper, CORSFilter corsFilter,
                             LoginService loginService, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
        this.corsFilter = corsFilter;
        this.loginService = loginService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers().permitAll()
                .antMatchers("/windbell/**").permitAll()
                .antMatchers("/msg/**").permitAll()
                .antMatchers("/menu/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/common/**").permitAll()
                .antMatchers("/project/**").permitAll()
                .antMatchers("/client/**").permitAll()
                .antMatchers("/swagger-ui.html",
                        "/v2/api-docs", // swagger api json
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources", // url used to retrieve api-docs
                        "/swagger-resources/configuration/security", // security option
                        "/swagger-resources/**",
                        "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(loginService, authenticationSuccessHandler), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(tokenHelper, loginService), JWTAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
