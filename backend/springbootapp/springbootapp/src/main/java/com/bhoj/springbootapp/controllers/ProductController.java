package com.bhoj.springbootapp.controllers;


import com.bhoj.springbootapp.DTO.BarCodeRequest;
import com.bhoj.springbootapp.beans.PackagedProduct;
import com.bhoj.springbootapp.exceptionHandler.UserCreationException;
import com.bhoj.springbootapp.serviceImpl.PackagedProductServiceImpl;
import com.bhoj.springbootapp.services.PackagedProductServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final PackagedProductServiceImpl packagedProductService;


    @GetMapping("/pkgd-item-number/{itemNumber}")
    public ResponseEntity<PackagedProduct> getPackagedProductByItemId(@PathVariable String itemNumber){

        PackagedProduct product = packagedProductService.getProductByItemId(itemNumber);

                return ResponseEntity.ok(product);
    }

    @PostMapping("/pkgd-item-bar-code")
    public ResponseEntity<PackagedProduct> getPackagedProductByUPC(@RequestBody BarCodeRequest request)
    {
        PackagedProduct product = packagedProductService.getProductByUPC(request);

        return  ResponseEntity.ok(product);

    }


}
