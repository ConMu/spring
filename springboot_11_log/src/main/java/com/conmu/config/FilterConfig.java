package com.conmu.config;


import com.conmu.filter.RequestBodyChannelFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestBodyChannelFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestBodyChannelFilter");
        return registration;
    }
}