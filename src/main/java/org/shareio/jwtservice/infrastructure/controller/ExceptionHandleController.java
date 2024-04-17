package org.shareio.jwtservice.infrastructure.controller;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shareio.jwtservice.exceptions.BadPasswordException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandleController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoUser(NoSuchElementException ex, WebRequest request) {
        String bodyOfResponse = "No user for given email has been found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {BadPasswordException.class})
    public ResponseEntity<Object> handleWrongPassword(BadPasswordException ex, WebRequest request) {
        String bodyOfResponse = "Wrong password";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {InvalidDataAccessResourceUsageException.class})
    public ResponseEntity<Object> handleDbError(InvalidDataAccessResourceUsageException ex, WebRequest request) {
        String bodyOfResponse = "Database error";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FAILED_DEPENDENCY, request);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex, @NonNull WebRequest request) {
        String bodyOfResponse = "Bad request";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Nullable
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (request instanceof ServletWebRequest servletWebRequest) {
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                if (this.logger.isWarnEnabled()) {
                    this.logger.warn("Response already committed. Ignoring: " + ex);
                }

                return null;
            }
        }

        return this.createResponseEntity(body, headers, statusCode);
    }

    protected ResponseEntity<Object> createResponseEntity(@Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode) {
        return new ResponseEntity<>(body, headers, statusCode);
    }
}
