package com.bhoj.springbootapp.controllers;

import com.bhoj.springbootapp.DTO.AddPackagedItemDTO;
import com.bhoj.springbootapp.DTO.CartProfile;
import com.bhoj.springbootapp.DTO.InitializeCart;
import com.bhoj.springbootapp.DTO.InitializeCartResponse;
import com.bhoj.springbootapp.beans.Cart;
import com.bhoj.springbootapp.serviceImpl.CartServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping("/")
    public ResponseEntity<InitializeCartResponse> initCart(@Valid @RequestBody InitializeCart initCart){
        log.info("Cart Initialization Request: {}", initCart);

        InitializeCartResponse response = cartService.initializeCart(initCart);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/getById/{cartId}")
    public ResponseEntity<CartProfile> getCartById(@PathVariable String cartId){
        log.info("Get Cart By Id Request: {}", cartId);

        CartProfile profile = cartService.getCartByCartId(cartId);
        System.out.println(profile);

        return ResponseEntity.ok(profile);
    }

    @PostMapping("/add-packaged")
    public ResponseEntity<Map<String, String>> addPackagedItem(
            @Valid @RequestBody AddPackagedItemDTO item){
        log.info("Add Packaged Item: {}", item);


        String result = cartService.addPackagedItemToCart(item);

        return  ResponseEntity.ok(Map.of("result", result));
    }

    @PostMapping("/increase-packaged")
    public ResponseEntity<String> increasePackagedItem(@Valid @RequestBody AddPackagedItemDTO item){

        log.info("Increase Packaged Item Qty: {}", item);
        String result = cartService.increasePackagedItemQuantity(item);

        return  ResponseEntity.ok(result);
    }
    @PostMapping("/decrease-packaged")
    public ResponseEntity<String> decreasePackagedItem(@Valid @RequestBody AddPackagedItemDTO item){
        log.info("decrease Packaged Item Qty: {}", item);

        String result = cartService.decreasePackagedItemQuantity(item);

        return ResponseEntity.ok(result);
    }
    @PatchMapping("/remove-packaged")
    public ResponseEntity<String> removePackagedItem(@Valid @RequestBody AddPackagedItemDTO item){
        log.info("Remove Packaged Item: {}", item);

        String result = cartService.removePackagedItemQuantity(item);

        return ResponseEntity.ok(result);
    }
}
