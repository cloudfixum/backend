package com.um.cloudfixum.cloudfixum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class CloudfixumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudfixumApplication.class, args);
    }

}
