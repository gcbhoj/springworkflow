package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.BarCodeRequest;
import com.bhoj.springbootapp.DTO.PackagedProductDTO;
import com.bhoj.springbootapp.beans.PackagedProduct;
import com.bhoj.springbootapp.exceptionHandler.UserCreationException;
import com.bhoj.springbootapp.repository.PackagedProductRepository;
import com.bhoj.springbootapp.services.PackagedProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackagedProductServiceImpl implements PackagedProductServices {

    private final PackagedProductRepository productRepo;
    @Override
    public PackagedProduct getProductByItemId(String itemNumber) {
        if(itemNumber == null || itemNumber.isBlank()){
            throw  new UserCreationException("ITEM NUMBER CANNOT BE BLANK");
        }

        PackagedProduct retrievedProduct = productRepo.findByItemNumber(itemNumber);

        if(retrievedProduct == null){
            throw  new UserCreationException("NO PRODUCT FOUND BY GIVEN ITEM NUMBER");
        }

        return retrievedProduct;


    }

    @Override
    public PackagedProduct getProductByUPC(BarCodeRequest request) {
        if(request.getUpc() == null || request.getUpc().isEmpty()){
            throw  new UserCreationException("MISSING BAR CODE VALUE");
        }

        PackagedProduct product = productRepo.findByUpc(request.getUpc());

        if(product == null){
            throw new UserCreationException("NO PRODUCT FOUND BY GIVEN BAR CODE VALUE");
        }

        return  product;
    }
}
