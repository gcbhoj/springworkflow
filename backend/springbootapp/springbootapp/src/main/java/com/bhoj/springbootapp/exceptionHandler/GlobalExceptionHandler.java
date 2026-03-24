package com.bhoj.springbootapp.exceptionHandler;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exp) {

        Set<String> errors = new HashSet<>();

        exp.getBindingResult().getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Validation failed")
                        .validationErrors(errors)
                        .build());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exp) {

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Null pointer exception occurred")
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exp) {

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Bad request")
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception exp) {

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Contact admin")
                        .error(exp.getMessage())
                        .build());
    }
}