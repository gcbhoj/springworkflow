package com.bhoj.springbootapp.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {

    NO_CODE(1000, HttpStatus.NOT_IMPLEMENTED, "NO CODE DEFINED"),

    INVALID_CREDENTIALS(1001, HttpStatus.BAD_REQUEST, "BAD CREDENTIALS"),

    RESOURCE_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "RESOURCE NOT FOUND"),

    ALREADY_EXISTS(1003, HttpStatus.CONFLICT, "RESOURCE ALREADY EXISTS"),

    INTERNAL_SERVER_ERROR(1004, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String description;

    BusinessErrorCodes(int errorCode, HttpStatus httpStatus, String description) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}