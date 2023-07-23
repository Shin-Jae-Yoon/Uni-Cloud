package com.devuni.unicloud.global.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class UniCloudException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    protected UniCloudException(String message) {
        super(message);
    }

    protected UniCloudException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
