package com.bhoj.springbootapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class InitializeCartResponse {

    private String cartId;
    private String retailerName;
    private BigDecimal budget;
    private String message;
}
