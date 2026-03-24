package com.bhoj.springbootapp.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@RequiredArgsConstructor
public class ExceptionResponse {

    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;
}
