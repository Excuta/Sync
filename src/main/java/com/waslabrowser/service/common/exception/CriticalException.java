package com.waslabrowser.service.common.exception;

/**
 * used for unexpected situations/ Developer errors such as data seeding
 */
public class CriticalException extends RuntimeException {
	public CriticalException() {
		super("SomethingBadHappened");
	}

	public CriticalException(String message) {
		super(message);
	}

	public CriticalException(String message, Throwable cause) {
		super(message, cause);
	}
}
