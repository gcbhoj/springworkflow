package com.bhoj.springbootapp.allTestSPM;

import com.bhoj.springbootapp.beans.Cart;
import com.bhoj.springbootapp.beans.CartItem;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.enums.CartStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

public class CartFunctionalTest {

    public CartFunctionalTest() {
    }

    @DisplayName("Test cart default values")
    @Test
    public void testCartDefaultValues() {
        System.out.println("testCartDefaultValues");

        // This test checks that a newly created cart has correct default values.
        // Expected result: status should be CREATED, budget should be zero, and cartItems should be empty.
        // This is important because default values define the initial valid state of the cart before any operations.

        Cart cart = new Cart();

        boolean expResult = true;

        boolean result = cart.getStatus() == CartStatus.CREATED
                && cart.getBudget().compareTo(BigDecimal.ZERO) == 0
                && cart.getCartItems().isEmpty();

        assertEquals(expResult, result);
    }

    @DisplayName("Test assigning user and retailer to cart")
    @Test
    public void testSetUserAndRetailer() {
        System.out.println("testSetUserAndRetailer");

        // This test verifies that the cart can correctly store user and retailer references.
        // Expected result: the assigned user and retailer should be retrievable from the cart.
        // This is important because the cart must be linked to the correct user and retailer for proper business logic.

        Cart cart = new Cart();
        User user = new User();
        Retailer retailer = new Retailer();

        cart.setUser(user);
        cart.setRetailer(retailer);

        boolean expResult = true;

        boolean result = cart.getUser() == user
                && cart.getRetailer() == retailer;

        assertEquals(expResult, result);
    }

    @DisplayName("Test assigning budget to cart")
    @Test
    public void testSetBudget() {
        System.out.println("testSetBudget");

        // This test checks whether the cart correctly stores a budget value.
        // Expected result: the budget stored in the cart should match the assigned BigDecimal value.
        // This is important because the budget is a key constraint for purchasing and financial calculations.

        Cart cart = new Cart();
        BigDecimal budget = new BigDecimal("150.00");

        cart.setBudget(budget);

        boolean expResult = true;

        boolean result = cart.getBudget().compareTo(new BigDecimal("150.00")) == 0;

        assertEquals(expResult, result);
    }

    @DisplayName("Test adding cart item to cart")
    @Test
    public void testAddCartItem() {
        System.out.println("testAddCartItem");

        // This test verifies that an item can be added to the cart and properly linked.
        // Expected result: the cart should contain the item, the item should reference the cart, and the size should increase.
        // This is important because adding items is a core functionality of the shopping/cart system.

        Cart cart = new Cart();
        CartItem item = new CartItem();

        cart.addCartItem(item);

        boolean expResult = true;

        boolean result = cart.getCartItems().contains(item)
                && item.getCart() == cart
                && cart.getCartItems().size() == 1;

        assertEquals(expResult, result);
    }

    @DisplayName("Test removing cart item from cart")
    @Test
    public void testRemoveCartItem() {
        System.out.println("testRemoveCartItem");

        // This test verifies that an item can be removed from the cart correctly.
        // Expected result: the item should no longer be in the cart, its cart reference should be null, and the cart should be empty.
        // This is important because removing items ensures correct cart updates and prevents incorrect data or transactions.

        Cart cart = new Cart();
        CartItem item = new CartItem();

        cart.addCartItem(item);
        cart.removeCartItem(item);

        boolean expResult = true;

        boolean result = !cart.getCartItems().contains(item)
                && item.getCart() == null
                && cart.getCartItems().isEmpty();

        assertEquals(expResult, result);
    }
}