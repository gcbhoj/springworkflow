package com.bhoj.springbootapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "FIRST NAME CANNOT BE EMPTY")
    @NotBlank(message = "FIRST NAME CANNOT BE BLANK")
    private String firstName;
    @NotEmpty(message = "FIRST NAME CANNOT BE EMPTY")
    @NotBlank(message = "FIRST NAME CANNOT BE BLANK")
    private String lastName;
    @Email(message = "EMAIL IS NOT FORMATTED")
    @NotEmpty(message = "USER EMAIL CANNOT BE EMPTY")
    @NotBlank(message = "USER EMAIL CANNOT BE BLANK")
    private String email;
}
