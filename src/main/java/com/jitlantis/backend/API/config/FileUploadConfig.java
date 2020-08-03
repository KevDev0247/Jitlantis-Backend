package com.jitlantis.backend.API.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("5MB");
        factory.setMaxRequestSize("10MB");

        return factory.createMultipartConfig();
    }
}
