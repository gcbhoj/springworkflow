package com.bhoj.springbootapp.allTestSPM;

import com.bhoj.springbootapp.beans.Cart;
import com.bhoj.springbootapp.beans.CartItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

public class CartPerformanceNonFunctionalTest {

    public CartPerformanceNonFunctionalTest() {
    }

    @DisplayName("Test performance of creating many cart objects")
    @Test
    public void testCartCreationPerformance() {
        System.out.println("testCartCreationPerformance");

        // This test measures how quickly the system can create many Cart objects.
        // Expected result: all Cart objects are created successfully within an acceptable time limit.
        // This is important because the system must handle multiple users creating carts without performance degradation.

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            Cart cart = new Cart();
            assertNotNull(cart);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        assertTrue(totalTime < 5000);
    }

    @DisplayName("Test performance of repeated budget assignment")
    @Test
    public void testBudgetAssignmentPerformance() {
        System.out.println("testBudgetAssignmentPerformance");

        // This test measures how efficiently the system can repeatedly assign budget values to a cart.
        // Expected result: budget updates are completed within the time limit without errors.
        // This is important because frequent updates to financial values should not slow down the system.

        Cart cart = new Cart();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            cart.setBudget(new BigDecimal(i + ".00"));
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        assertTrue(totalTime < 5000);
    }

    @DisplayName("Test performance of adding many cart items")
    @Test
    public void testCartItemAdditionPerformance() {
        System.out.println("testCartItemAdditionPerformance");

        // This test measures how efficiently multiple items can be added to a cart.
        // Expected result: all items are added successfully and total count matches expected value within time limit.
        // This is important because users may add many items to a cart, and the system must remain responsive.

        Cart cart = new Cart();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            CartItem item = new CartItem();
            cart.addCartItem(item);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        assertEquals(1000, cart.getCartItems().size());
        assertTrue(totalTime < 5000);
    }

    @DisplayName("Test performance of removing many cart items")
    @Test
    public void testCartItemRemovalPerformance() {
        System.out.println("testCartItemRemovalPerformance");

        // This test measures how efficiently items can be removed from the cart repeatedly.
        // Expected result: all items are removed successfully and the cart becomes empty within time limit.
        // This is important to ensure that cart updates remain efficient when users modify their selections.

        Cart cart = new Cart();

        for (int i = 0; i < 1000; i++) {
            CartItem item = new CartItem();
            cart.addCartItem(item);
        }

        long startTime = System.currentTimeMillis();

        while (!cart.getCartItems().isEmpty()) {
            cart.removeCartItem(cart.getCartItems().get(0));
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        assertTrue(cart.getCartItems().isEmpty());
        assertTrue(totalTime < 5000);
    }

    @DisplayName("Test performance of handling many carts with different budgets")
    @Test
    public void testBulkCartDataHandlingPerformance() {
        System.out.println("testBulkCartDataHandlingPerformance");

        // This test measures how well the system handles many carts with different data values.
        // Expected result: all carts correctly store their assigned budgets within the time limit.
        // This is important because the system must scale and handle multiple carts and data variations efficiently.

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 2000; i++) {
            Cart cart = new Cart();
            cart.setBudget(new BigDecimal(i + ".00"));

            boolean result = cart.getBudget().compareTo(new BigDecimal(i + ".00")) == 0;

            assertTrue(result);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        assertTrue(totalTime < 5000);
    }
}