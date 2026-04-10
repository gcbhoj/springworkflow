package com.bhoj.springbootapp.services;


import com.bhoj.springbootapp.DTO.AddPackagedItemDTO;
import com.bhoj.springbootapp.DTO.CartProfile;
import com.bhoj.springbootapp.DTO.InitializeCart;
import com.bhoj.springbootapp.DTO.InitializeCartResponse;

public interface CartServices {

    InitializeCartResponse initializeCart(InitializeCart initCart);
    CartProfile getCartByCartId(String cartId);
    String addPackagedItemToCart(AddPackagedItemDTO item);
    String increasePackagedItemQuantity(AddPackagedItemDTO item);
    String decreasePackagedItemQuantity(AddPackagedItemDTO item);
    String removePackagedItemQuantity(AddPackagedItemDTO item);
}
