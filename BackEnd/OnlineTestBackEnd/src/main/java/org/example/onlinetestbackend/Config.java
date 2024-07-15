package org.example.onlinetestbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    /**
     * 配置跨域资源共享（CORS）
     * @param registry 用于配置CORS的注册表
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有路径的请求进行跨域处理
                .allowedOriginPatterns("*") // 允许所有域名进行跨域调用
                .allowedHeaders(CorsConfiguration.ALL) // 允许所有请求头
                .allowedMethods(CorsConfiguration.ALL) // 允许所有HTTP方法
                .allowCredentials(true) // 允许携带认证信息
                .maxAge(3600); // 1小时内不需要再进行预检（发OPTIONS请求）
    }
}