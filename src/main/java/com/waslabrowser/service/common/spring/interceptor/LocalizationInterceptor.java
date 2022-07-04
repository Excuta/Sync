package com.waslabrowser.service.common.spring.interceptor;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class LocalizationInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LocalizationInterceptor.class);
    private static final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        var language = resolver.resolveLocale(request).getLanguage();
        var countryCode = "EG"; // TODO: get country code from JWT claims
        var locale = new Locale(language, countryCode);
        LocaleContextHolder.setLocale(locale);
        log.info("Request Localized: {}", locale);
        return true;
    }
}
