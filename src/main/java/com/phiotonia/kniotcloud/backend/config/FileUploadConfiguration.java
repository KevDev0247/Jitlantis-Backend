package com.phiotonia.kniotcloud.backend.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

public class FileUploadConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("5MB"));
        factory.setMaxRequestSize(DataSize.parse("10MB"));

        return factory.createMultipartConfig();
    }
}
