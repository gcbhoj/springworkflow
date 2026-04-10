package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.DTO.BarCodeRequest;
import com.bhoj.springbootapp.DTO.PackagedProductDTO;
import com.bhoj.springbootapp.beans.PackagedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface PackagedProductServices {

    PackagedProduct getProductByItemId(String itemNumber);
    PackagedProduct getProductByUPC(BarCodeRequest request);
}
