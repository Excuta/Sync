package com.waslabrowser.service.common.util;

import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.constraints.NotNull;

public final class Localizer {
    public static boolean is(@NotNull String lang) {
        return LocaleContextHolder.getLocale().getLanguage().equals(lang);
    }

    public static String getCountry() {
        throw new RuntimeException("");
        // Uncomment and use after implementing -> {TODO: get country code from JWT claims}
        // return LocaleContextHolder.getLocale().getCountry();
    }

    public static String getLang() {
        return LocaleContextHolder.getLocale().getLanguage();
    }
}
