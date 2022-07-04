package com.waslabrowser.service.common.spring.validation.countrycode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryCode {
	String message() default "InvalidCountryCode";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}