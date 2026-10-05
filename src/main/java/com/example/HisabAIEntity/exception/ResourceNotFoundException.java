package com.example.HisabAIEntity.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String entityName, Object id) {
        super(HttpStatus.NOT_FOUND, entityName + " with id " + id + " was not found");
    }

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
