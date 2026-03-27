package com.bhoj.springbootapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CartProfile {

    private String cartId;
    private  String userId;
    private List<PackagedProductDTO> packagedProductList = new ArrayList<>();
    private List<UnpackagedProductDTO> unpackagedProductList =new ArrayList<>();

    private BigDecimal hstAmount;
    private BigDecimal cartTotal;


}
