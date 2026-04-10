package com.bhoj.springbootapp.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RetailerRegistrationRequest {
    @NotEmpty(message = "RETAILER NAME CANNOT BE EMPTY")
    private String retailerName;
    @NotEmpty(message = "RETAILER WEB URL CANNOT BE EMPTY")
    private String retailerURL;
}
