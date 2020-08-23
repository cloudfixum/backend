package com.um.cloudfixum.cloudfixum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                        .allowedOrigins("http://localhost:3000", "https://cloudfixum-develop.herokuapp.com", "https://cloudfixum.herokuapp.com")
                        .allowedHeaders("*")
                        .exposedHeaders("Prev", "Next", "CurrentPage", "Size", "TotalPages", "TotalRecords");
            }
        };
    }
}

