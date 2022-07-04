package com.waslabrowser.service.common.spring.advice;

import com.waslabrowser.service.common.exception.CriticalException;
import com.waslabrowser.service.common.exception.RequestInputValidationException;
import com.waslabrowser.service.common.spring.advice.error.ApiError;
import com.waslabrowser.service.common.spring.advice.error.ErrorBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler({RequestInputValidationException.class})
    public ResponseEntity<ErrorBody> handleInputValidationException(final Exception exception, final WebRequest request) {
        log.error("Input was invalid", exception);
        final RequestInputValidationException requestInputValidationException = (RequestInputValidationException) exception;
        if (requestInputValidationException.getBindingResult() != null)
            return ResponseEntity.badRequest().body(ErrorBody.from(requestInputValidationException.getBindingResult()));
        else if (requestInputValidationException.getExceptions() != null)
            return ResponseEntity.badRequest().body(ErrorBody.from(requestInputValidationException.getExceptions()));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBody(new ApiError("ValidationException", null)));
    }

    @ExceptionHandler({CriticalException.class})
    public ResponseEntity<ErrorBody> handleCriticalException(final Exception exception, final WebRequest request) {
        log.error("Critical Exception something was not found in DB", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBody(new ApiError(exception.getMessage(), "Required Data was not inserted in database")));
    }



    @ExceptionHandler
    public ResponseEntity<ErrorBody> logGlobalError(final Throwable throwable) {
        final String ErrorCode = "Uncaught Exception: Operation Failed";
        log.error(ErrorCode, throwable);
        var msg = throwable.getMessage();
        if (msg == null) msg = "Unknown Error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBody(new ApiError(ErrorCode, msg)));
    }
}
