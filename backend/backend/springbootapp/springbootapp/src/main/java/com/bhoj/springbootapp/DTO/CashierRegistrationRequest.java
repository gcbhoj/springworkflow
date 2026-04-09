package com.bhoj.springbootapp.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CashierRegistrationRequest {
    @NotEmpty(message = "RETAILER ID CANNOT BE EMPTY")
    private  String retailerId;
    @NotEmpty(message = "EMPLOYEE ID CANNOT BE EMPTY")
    private String employeeId;
    @NotEmpty(message = "EMPLOYEE NAME CANNOT BE EMPTY")
    private String employeeName;
}
