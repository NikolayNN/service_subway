package com.example.service_subway.core.exception;

public abstract class AppNotFoundException extends RuntimeException {

    public AppNotFoundException() {
    }

    public AppNotFoundException(String message) {
        super(message);
    }

    public AppNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppNotFoundException(Throwable cause) {
        super(cause);
    }

    public AppNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
