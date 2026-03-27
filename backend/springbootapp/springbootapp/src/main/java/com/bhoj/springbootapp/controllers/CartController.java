package com.bhoj.springbootapp.controllers;

import com.bhoj.springbootapp.DTO.AddPackagedItemDTO;
import com.bhoj.springbootapp.DTO.CartProfile;
import com.bhoj.springbootapp.DTO.InitializeCart;
import com.bhoj.springbootapp.DTO.InitializeCartResponse;
import com.bhoj.springbootapp.beans.Cart;
import com.bhoj.springbootapp.serviceImpl.CartServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping("/")
    public ResponseEntity<InitializeCartResponse> initCart(@Valid @RequestBody InitializeCart initCart){

        InitializeCartResponse response = cartService.initializeCart(initCart);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/getById")
    public ResponseEntity<CartProfile> getCartById(String cartId){
        CartProfile profile = cartService.getCartByCartId(cartId);

        return ResponseEntity.ok(profile);
    }

    @PostMapping("/add-packaged")
    public ResponseEntity<String> addPackagedItem(@Valid @RequestBody AddPackagedItemDTO item){

        String result = cartService.addPackagedItemToCart(item);

        return  ResponseEntity.ok(result);
    }

    @PostMapping("/increase-packaged")
    public ResponseEntity<String> increasePackagedItem(@Valid @RequestBody AddPackagedItemDTO item){
        String result = cartService.increasePackagedItemQuantity(item);

        return  ResponseEntity.ok(result);
    }
    @PostMapping("/decrease-packaged")
    public ResponseEntity<String> decreasePackagedItem(@Valid @RequestBody AddPackagedItemDTO item){
        String result = cartService.decreasePackagedItemQuantity(item);

        return ResponseEntity.ok(result);
    }
}
