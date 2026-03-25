package com.bhoj.springbootapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.Locked.Read;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserProfile {
    @NotEmpty(message = "USER ID CANNOT BE BLANK")
    private String userId;
    @NotEmpty(message = "FIRST NAME CANNOT BE BLANK")
    private String firstName;
    @NotEmpty(message = "LAST NAME CANNOT BE BLANK")
    private String lastName;
    @NotEmpty(message = "EMAIL CANNOT BE BLANK")
    @Email(message = "EMAIL IS NOT FORMATTED")
    private String email;

    protected String activationDate;
    protected String lastUpdated;

}
