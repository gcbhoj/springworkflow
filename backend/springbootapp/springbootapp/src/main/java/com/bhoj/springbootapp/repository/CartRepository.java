package com.bhoj.springbootapp.repository;

import com.bhoj.springbootapp.beans.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {
    Cart getCartByUserUserId(String userId);
}
