package com.apple.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @description: 系统跨域解决
 * @author: fengx
 * @create: 2020-09-05 11:11
 */
@Configuration
public class AppCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 跨域配置
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许哪些请求头
        configuration.addAllowedHeader("*");
        // 允许哪些请求方式
        configuration.addAllowedMethod("*");
        // 来源方式
        configuration.addAllowedOrigin("*");
        // 是否允许携带cookie
        configuration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }
}
