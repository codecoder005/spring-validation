package com.popcorn.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class GlobalAPIControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        log.error("GlobalAPIControllerAdvice::Exception {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of(
                        "message", e.getMessage(),
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "timestamp", ZonedDateTime.now()
                )
        );
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        log.error("GlobalAPIControllerAdvice::HttpMessageConversionException {}", ex.getMessage());
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBindException(BindException ex) {
        log.error("GlobalAPIControllerAdvice::BindException {}", ex.getMessage());
        Map<String, Object> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "One or more fields are failing validation criteria");
        response.put("errors", errors);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
