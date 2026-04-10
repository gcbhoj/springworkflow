package com.bhoj.springbootapp.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class InitializeCart {
    @NotEmpty(message = "USER ID CANNOT BE EMPTY")
    private String userId;
    @NotEmpty(message = "RETAILER ID CANNOT BE EMPTY")
    private String retailerId;
    @DecimalMin(value = "0.0", inclusive = true, message = "BUDGET CANNOT BE NEGATIVE")
    @Builder.Default
    private BigDecimal budget = BigDecimal.ZERO;

}
