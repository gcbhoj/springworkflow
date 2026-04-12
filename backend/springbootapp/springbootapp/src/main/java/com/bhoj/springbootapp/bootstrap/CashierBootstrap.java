package com.bhoj.springbootapp.bootstrap;

import com.bhoj.springbootapp.beans.Cashier;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.repository.CashierRepository;
import com.bhoj.springbootapp.serviceImpl.RetailerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(4)
public class CashierBootstrap implements CommandLineRunner {

    private final CashierRepository cashierRepo;
    private final RetailerServiceImpl retailerService;

    @Override
    public void run(String... args) throws Exception {

        Retailer retailer = retailerService.getRetailerByName("Walmart");

        Cashier cashier = Cashier.builder()
                .employeeId("ABC")
                .employeeName("Daniel")
                .retailer(retailer)
                .build();

        cashierRepo.save(cashier);



    }
}
