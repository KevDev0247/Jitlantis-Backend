package com.phiotonia.kniotcloud.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.phiotonia.kniotcloud.backend")
public class KnIotCloudBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnIotCloudBackendApplication.class, args);
    }
}
