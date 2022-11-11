package com.conmu.config;

import com.conmu.inteceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有都拦截
//        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
    }
}
