package com.apisecurity.auth;

public class SecurityException extends RuntimeException {
    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
