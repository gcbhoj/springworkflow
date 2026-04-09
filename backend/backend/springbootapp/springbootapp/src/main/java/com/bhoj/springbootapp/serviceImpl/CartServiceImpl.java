package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.*;
import com.bhoj.springbootapp.beans.*;
import com.bhoj.springbootapp.calculatorService.CalculatorServices;
import com.bhoj.springbootapp.enums.CartStatus;
import com.bhoj.springbootapp.exceptionHandler.UserCreationException;
import com.bhoj.springbootapp.repository.CartRepository;
import com.bhoj.springbootapp.services.CartServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartServices {

    private final CartRepository cartRepo;
    private final UserServicesImpl userService;
    private final RetailerServiceImpl retailerService;
    private final PackagedProductServiceImpl packagedProductService;

    private final CalculatorServices calc;

    @Transactional
    @Override
    public InitializeCartResponse initializeCart(InitializeCart initCart) {


        User user = userService.verifyUserStatus(initCart.getUserId());

        Cart existingCart = getCartByUserId(initCart.getUserId());

        if (existingCart != null) {
            throw new UserCreationException("USER ALREADY HAS AN ACTIVE CART");
        }

        Retailer retailer = retailerService.verifyRetailerStatus(initCart.getRetailerId());

        String retailerName = retailer.getRetailerName();

        Cart cart = Cart.builder()
                .retailer(retailer)
                .user(user)
                .budget(initCart.getBudget().compareTo(BigDecimal.ZERO) < 0
                        ? BigDecimal.ZERO
                        : initCart.getBudget())
                .build();

        cartRepo.save(cart);

        return InitializeCartResponse.builder()
                .cartId(cart.getCartId())
                .retailerName(retailerName)
                .budget(cart.getBudget())
                .message("HAPPY SHOPPING")
                .build();
    }

    @Override
    @Transactional
    public String addPackagedItemToCart(AddPackagedItemDTO item) {

        Cart cart = getCartById(item.getCartId());
        if(cart == null){
            throw  new UserCreationException("CANNOT FIND CART BY GIVEN ID");
        }

        if(cart.getCartItems().isEmpty()){
            cart.setStatus(CartStatus.IN_PROGRESS);
        }

        PackagedProduct product = packagedProductService.getProductByItemId(item.getItemNumber());

        if(product == null){
            throw new UserCreationException("CANNOT FIND PACKAGED PRODUCT BY GIVEN ITEM ID");
        }

        CartItem existingItem = cart.getCartItems().stream()
                .filter(i->item.getItemNumber().equals(i.getProduct().getItemNumber()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            Long currentQty = existingItem.getQuantity() == null ? 0L : existingItem.getQuantity();
            existingItem.setQuantity(currentQty + 1L);
            return "PRODUCT QUANTITY UPDATED";
        }

        CartItem cartItem = CartItem.builder()
                .product(product)
                .build();

        cart.addCartItem(cartItem);


        cartRepo.save(cart);
        System.out.println("Saved cart items: " + cart.getCartItems().size());

        return "PRODUCT ADDED TO CART SUCCESSFULLY";
    }

    @Override
    @Transactional
    public String increasePackagedItemQuantity(AddPackagedItemDTO item) {

        Cart cart = getCartById(item.getCartId());

        if (cart == null) {
            throw new UserCreationException("CART ID CANNOT BE NULL");
        }

        CartItem cartItem = cart.getCartItems().stream()
                .filter(ci -> item.getItemNumber().equals(ci.getProduct().getItemNumber()))
                .findFirst()
                .orElseThrow(() -> new UserCreationException("PRODUCT NOT FOUND IN CART"));

        // Increase quantity
        cartItem.setQuantity(cartItem.getQuantity() + 1L);

        cartRepo.save(cart);

        return "PRODUCT QUANTITY INCREASED SUCCESSFULLY";
    }

    @Override
    @Transactional
    public String decreasePackagedItemQuantity(AddPackagedItemDTO item) {
        Cart cart = getCartById(item.getCartId());


        CartItem cartItem = cart.getCartItems().stream()
                .filter(ci -> item.getItemNumber().equals(ci.getProduct().getItemNumber()))
                .findFirst()
                .orElseThrow(() -> new UserCreationException("PRODUCT NOT FOUND IN CART"));

        Long currentQty = cartItem.getQuantity();

        if (currentQty <= 1) {
            throw new UserCreationException(
                    "PRODUCT CANNOT BE SET TO 0 OR LESS. USE REMOVE INSTEAD"
            );
        }

        // decrease quantity
        cartItem.setQuantity(cartItem.getQuantity() - 1L);

        cartRepo.save(cart);

        return "PRODUCT QUANTITY DECREASED SUCCESSFULLY";
    }

    @Override
    public String removePackagedItemQuantity(AddPackagedItemDTO item) {

        Cart cart = getCartById(item.getCartId());

        CartItem cartItem = cart.getCartItems().stream()
                .filter(ci -> item.getItemNumber().equals(ci.getProduct().getItemNumber()))
                .findFirst()
                .orElseThrow(() -> new UserCreationException("PRODUCT NOT FOUND IN CART"));


        cart.removeCartItem(cartItem);

        cartRepo.save(cart);


        return "PRODUCT SUCCESSFULLY REMOVED FROM CART";


    }

    @Transactional
    @Override
    public CartProfile getCartByCartId(String cartId){

        Cart cart = getCartById(cartId);

        System.out.println("Fetched cart items: " + cart.getCartItems().size());

        // Packaged products
        List<PackagedProductDTO> packagedProducts = cart.getCartItems()
                .stream()
                .map(ci -> Hibernate.unproxy(ci.getProduct()))
                .filter(p -> p instanceof PackagedProduct)
                .map(p -> {
                    PackagedProduct prod = (PackagedProduct) p;
                    return PackagedProductDTO.builder()
                            .productId(prod.getProductId())
                            .productName(prod.getProductName())
                            .itemNumber(prod.getItemNumber())
                            .imageURL(prod.getImageURL())
                            .unitPrice(prod.getPrice())
                            .quantity(cart.getCartItems().stream()
                                    .filter(ci -> ci.getProduct().getProductId().equals(prod.getProductId()))
                                    .findFirst()
                                    .get()
                                    .getQuantity())
                            .build();
                })
                .toList();

        // Unpackaged products
        List<UnpackagedProductDTO> unpackagedProducts = cart.getCartItems()
                .stream()
                .map(ci -> Hibernate.unproxy(ci.getProduct()))
                .filter(p -> p instanceof UnpackagedProduct)
                .map(p -> {
                    UnpackagedProduct prod = (UnpackagedProduct) p;
                    return UnpackagedProductDTO.builder()
                            .productId(prod.getProductId())
                            .productName(prod.getProductName())
                            .itemNumber(prod.getItemNumber())
                            .imageUrl(prod.getImageURL())
                            .unitPrice(prod.getPrice())
                            .weight(prod.getWeight())
                            .build();
                })
                .toList();

        BigDecimal packagedProductTotal = calc.calculateTotalCostPackagedProduct(packagedProducts);
        BigDecimal unpackagedProductTotal = calc.calculateTotalCostUnPackagedProduct(unpackagedProducts);

        BigDecimal subTotal = packagedProductTotal.add(unpackagedProductTotal);

        BigDecimal hstAmount = calc.calculateHSTAmount(subTotal);

        BigDecimal cartTotal = subTotal.add(hstAmount);




        // Return DTO
        return CartProfile.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUser().getUserId())
                .packagedProductList(packagedProducts)
                .unpackagedProductList(unpackagedProducts)
                .subTotal(subTotal)
                .hstAmount(hstAmount)
                .cartTotal(cartTotal)
                .build();
    }

private Cart getCartByUserId(String userId){

        return   cartRepo.getCartByUserUserId(userId);
}

private Cart getCartById(String cartId){
        return cartRepo.findById(cartId)
                .orElseThrow(()-> new UserCreationException("NO CART FOUND BY GIVEN ID"));


    }
}
