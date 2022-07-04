package com.waslabrowser.service.common.spring.advice.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ApiError {
	
	@JsonProperty("fieldName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String fieldName;
	
	@JsonProperty("error")
	@NotNull
	private String errorCode;
	
	@JsonProperty("error_description")
	@NotNull
	private String errorDescription;

	public ApiError() {
	}

	public ApiError(@NotNull String errorCode, @NotNull String errorDescriptions) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescriptions;
	}

	public ApiError(@NotNull String fieldName, @NotNull String errorCode, @NotNull String errorDescriptions) {
		this.fieldName = fieldName;
		this.errorCode = errorCode;
		this.errorDescription = errorDescriptions;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ApiError apiError = (ApiError) o;
		return Objects.equals(fieldName, apiError.fieldName)
				&& Objects.equals(errorCode, apiError.errorCode) 
				&& Objects.equals(errorDescription, apiError.errorDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldName, errorCode, errorDescription);
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescriptions() {
		return errorDescription;
	}

	public void setErrorDescriptions(String errorDescriptions) {
		this.errorDescription = errorDescriptions;
	}

	@Override
	public String toString() {
		return "ApiError [fieldName=" + fieldName + ", errorCode=" + errorCode + ", errorDescription="
				+ errorDescription + "]";
	}
}
