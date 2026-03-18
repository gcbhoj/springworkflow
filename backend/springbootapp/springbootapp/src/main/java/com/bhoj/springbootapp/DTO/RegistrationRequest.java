package com.bhoj.springbootapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class RegistrationRequest {

    private String firstName;
    private String email;
}
