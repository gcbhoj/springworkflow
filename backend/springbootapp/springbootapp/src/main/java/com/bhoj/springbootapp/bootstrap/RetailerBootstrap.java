package com.bhoj.springbootapp.bootstrap;

import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.repository.RetailerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetailerBootstrap implements CommandLineRunner {

    private final RetailerRepository retailerRepo;

    @Override
    public void run(String... args) throws Exception {

        Retailer  retailer = Retailer.builder()
                .retailerName("Walmart")
                .retailerWebURL("www.walmart.com")
                .build();

        retailerRepo.save(retailer);

    }
}
