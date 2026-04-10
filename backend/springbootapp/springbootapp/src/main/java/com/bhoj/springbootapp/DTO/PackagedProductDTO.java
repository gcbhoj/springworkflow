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
public class PackagedProductDTO {

    private String productId;
    private String productName;
    private String itemNumber;
    private String imageURL;
    private Long quantity;
    private BigDecimal unitPrice;

}
