package com.bhoj.springbootapp.exceptionHandler;

public class UserCreationException extends RuntimeException {

    public UserCreationException(String message) {
        super(message);
    }
}