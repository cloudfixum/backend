package com.um.cloudfixum.cloudfixum.config;

import com.um.cloudfixum.cloudfixum.common.Constant;
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
                        .addMapping(Constant.BAR_ASTERISK)
                        .allowedMethods(Constant.ALLOWED_METHODS)
                        .allowedOrigins(Constant.HTTP_LOCALHOST, Constant.HTTP_DEVELOP_HEROKU_FRONT, Constant.HTTP_MASTER_HEROKU_FRONT)
                        .allowedHeaders(Constant.ASTERISK)
                        .exposedHeaders(Constant.EXPOSED_HEADERS);
            }
        };
    }
}

