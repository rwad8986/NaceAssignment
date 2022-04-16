package com.luxsoft.nace.advice;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class NaceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNaceNotFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Nace object not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
