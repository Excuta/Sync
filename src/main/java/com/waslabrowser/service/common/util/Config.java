package com.waslabrowser.service.common.util;

import java.text.SimpleDateFormat;

//TODO inject as abstraction for Environment
public class Config {
	public static final String LANGUAGE_KEY = "LanguageKey";
	public static final int MAX_PAGE_SIZE = 30;
	public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public static final String ID_KEY = "userId";
	public static final String CLIENT_ID_KEY = "ClientId";

	public static final String SUB = "sub";
	public static final String USER_NAME = "preferred_username";
	public static final String COUNTRY_CODE = "countryCode";

	public static final String INVALID_VALUE_TEMPLATE_EN = "Invalid `{0}` provided!";
	public static final String INVALID_VALUE_TEMPLATE_AR = "`{0}` غير صحيح!";
	public static final String IS_BLOCKED = "isBlocked";
}
