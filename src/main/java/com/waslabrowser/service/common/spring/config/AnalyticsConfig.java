package com.waslabrowser.service.common.spring.config;

import com.segment.analytics.Analytics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class AnalyticsConfig {

    @Value("${segment.writekey}")
    String writeKey;

    @Bean
    public Analytics analytics() {
        return Analytics.builder(writeKey).flushQueueSize(125).flushInterval(5, TimeUnit.SECONDS).build();
    }
}