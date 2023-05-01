package com.example.service_subway.core.api;

import com.example.service_subway.core.exception.AppNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {AppNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleException(AppNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
    }

    private record ErrorMessage(String message) {
    }
}
