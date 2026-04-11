package com.bhoj.springbootapp.allTestSPM;

import com.bhoj.springbootapp.beans.Cart;
import com.bhoj.springbootapp.beans.CartItem;
import com.bhoj.springbootapp.beans.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

public class CartSmokeTest {

    public CartSmokeTest() {
    }

    @DisplayName("Smoke test cart can be created")
    @Test
    public void testCartCanBeCreated() {
        System.out.println("testCartCanBeCreated");

        // This test checks whether a Cart object can be created without errors.
        // Expected result: the Cart object is successfully instantiated and not null.
        // This is important because object creation is the first step before any other cart operations can occur.

        Cart cart = new Cart();

        assertNotNull(cart);
    }

    @DisplayName("Smoke test cart can set budget")
    @Test
    public void testCartCanSetBudget() {
        System.out.println("testCartCanSetBudget");

        // This test checks whether a budget value can be assigned to the cart.
        // Expected result: the budget field is set successfully and not null.
        // This is important because the system must be able to handle basic financial data without failure.

        Cart cart = new Cart();
        cart.setBudget(new BigDecimal("100.00"));

        assertNotNull(cart.getBudget());
    }

    @DisplayName("Smoke test cart can assign user")
    @Test
    public void testCartCanAssignUser() {
        System.out.println("testCartCanAssignUser");

        // This test checks whether a user can be assigned to the cart.
        // Expected result: the user reference is successfully stored and not null.
        // This is important because every cart must be associated with a user for proper system functionality.

        Cart cart = new Cart();
        User user = new User();

        cart.setUser(user);

        assertNotNull(cart.getUser());
    }

    @DisplayName("Smoke test cart can add item")
    @Test
    public void testCartCanAddItem() {
        System.out.println("testCartCanAddItem");

        // This test checks whether an item can be added to the cart without causing errors.
        // Expected result: the cart item list exists and the operation completes successfully.
        // This is important because adding items is a core feature and must work before deeper testing is performed.

        Cart cart = new Cart();
        CartItem item = new CartItem();

        cart.addCartItem(item);

        assertNotNull(cart.getCartItems());
    }

    @DisplayName("Smoke test cart can remove item")
    @Test
    public void testCartCanRemoveItem() {
        System.out.println("testCartCanRemoveItem");

        // This test checks whether an item can be removed from the cart without errors.
        // Expected result: the operation completes successfully and the cart item list remains valid.
        // This is important because removing items is a basic system operation that must not fail.

        Cart cart = new Cart();
        CartItem item = new CartItem();

        cart.addCartItem(item);
        cart.removeCartItem(item);

        assertNotNull(cart.getCartItems());
    }
}