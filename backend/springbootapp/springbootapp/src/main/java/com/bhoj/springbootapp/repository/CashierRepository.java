package com.bhoj.springbootapp.repository;

import com.bhoj.springbootapp.DTO.CashierProfile;
import com.bhoj.springbootapp.beans.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashierRepository extends JpaRepository<Cashier,String> {

    List<Cashier> getAllCashiersByRetailerRetailerId(String retailerId);}
