package com.bhoj.springbootapp.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exp) {

        log.error("Method Argument Not Valid Exception:", exp);

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

        log.error("Null Pointer Exception: ", exp);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Null pointer exception occurred")
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException exp) {

        log.error("Bad Request Exception: ", exp);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Bad request")
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception exp) {

        log.error("Exception : ", exp);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ExceptionResponse> handle(UserCreationException ex) {

        log.error("User Actions Exception: ", ex);

        return ResponseEntity.status(BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription(ex.getMessage())
                        .build());
    }

}