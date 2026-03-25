package com.bhoj.springbootapp.controllers;

import com.bhoj.springbootapp.DTO.RegisterRetailerRequest;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.serviceImpl.RetailerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("retailer")
public class RetailerController {

    private final RetailerServiceImpl retailerServiceImpl;

    @PostMapping("/retailer-register")
    public ResponseEntity<String> addNewRetailer(@RequestBody RegisterRetailerRequest request){

        retailerServiceImpl.saveRetailer(request);

        return ResponseEntity.ok("Retailer added successfully");

    }

    @GetMapping("/getRetailerById")
    public ResponseEntity<Retailer> getRetailerById(
            @RequestParam String retailerId
    ) {
        return retailerServiceImpl.getRetailerById(retailerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<Retailer>> getAllRetailers() {
        return ResponseEntity.ok(retailerServiceImpl.getAllRetailers());
    }
}
