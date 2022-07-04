package com.waslabrowser.service.common.spring.config;

import com.waslabrowser.service.common.spring.interceptor.LocalizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocalizationInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LocalizationInterceptor localizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localizationInterceptor).addPathPatterns("/**");
    }
}
