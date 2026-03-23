package com.bhoj.springbootapp.controllers;

import com.bhoj.springbootapp.DTO.RegisterRetailerRequest;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.repository.RetailerRepository;
import com.bhoj.springbootapp.services.RetailerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("retailer")
public class RetailerController {

    private final RetailerService retailerService;

    @PostMapping("/retailer-register")
    public ResponseEntity<String> addNewRetailer(@RequestBody RegisterRetailerRequest request){

        retailerService.saveRetailer(request);

        return ResponseEntity.ok("Retailer added successfully");

    }

    @GetMapping("/getRetailerById")
    public ResponseEntity<Retailer> getRetailerById(
            @RequestParam String retailerId
    ) {
        return retailerService.getRetailerById(retailerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
