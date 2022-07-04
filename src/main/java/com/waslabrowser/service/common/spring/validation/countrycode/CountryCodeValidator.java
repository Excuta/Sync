package com.waslabrowser.service.common.spring.validation.countrycode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if (s == null || s.isBlank()) return false;
		var countryCodes = Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA2);
		return countryCodes.contains(s.toUpperCase());
	}
}
