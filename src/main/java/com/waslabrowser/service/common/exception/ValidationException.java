package com.waslabrowser.service.common.exception;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public class ValidationException extends RuntimeException {
	@NotNull
	private String description;

	public ValidationException(String message) {
		super(message);
		description = "";
	}

	public ValidationException(String message, String description) {
		super(message);
		this.description = description;
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	@Nullable
	public String getDescription() {
		return description;
	}
}
