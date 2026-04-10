package com.bhoj.springbootapp.DTO;

import com.bhoj.springbootapp.enums.RetailerStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RetailerProfile {

    @NotEmpty(message = "RETAILER ID CANNOT BE BLANK")
    private String retailerId;
    @NotEmpty(message = "RETAILER NAME CANNOT BE BLANK")
    private String retailerName;
    @NotEmpty(message = "RETAILER WEB URL CANNOT BE BLANK")
    private String retailerWebURL;
    private RetailerStatus status;
}
