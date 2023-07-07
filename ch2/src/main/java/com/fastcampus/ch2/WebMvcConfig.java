package com.fastcampus.ch2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor()) // interceptor로 등록
                .addPathPatterns("/**") // interceptor가 적용될 대상을 정함. -> 모든 대상
                .excludePathPatterns("/css/**", "/js/**"); // 제외할 것. css파일이나 js파일 제외
    }
}
