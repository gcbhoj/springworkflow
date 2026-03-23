package com.bhoj.springbootapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterRetailerRequest {

    private String retailerName;
    private String retailerURL;
}
