package com.bhoj.springbootapp.controllers;

import com.bhoj.springbootapp.DTO.RetailerProfile;
import com.bhoj.springbootapp.DTO.RetailerRegistrationRequest;
import com.bhoj.springbootapp.DTO.RetailerRegistrationResponse;
import com.bhoj.springbootapp.serviceImpl.RetailerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("retailer")
public class RetailerController {

    private final RetailerServiceImpl retailerService;


    @GetMapping("/")
    public  ResponseEntity<List<RetailerProfile>> getAllRetailers(){
         List<RetailerProfile> retailers =  retailerService.getAllRetailers();

         return ResponseEntity.ok(retailers);
    }
    @GetMapping("/getRetailerById")
    public ResponseEntity<RetailerProfile> getRetailerById(@RequestParam String retailerId){
        RetailerProfile profile = retailerService.getRetailerById(retailerId);

        return ResponseEntity.ok(profile);

    }

    @PostMapping("/register-retailer")
    public ResponseEntity<RetailerRegistrationResponse> addNewRetailer(@Valid @RequestBody RetailerRegistrationRequest request){

        RetailerRegistrationResponse response = retailerService.addNewRetailer(request);

        return  ResponseEntity.ok(response);
    }

    @PatchMapping("/edit-retailer")
    public ResponseEntity<String> editRetailer(@Valid @RequestBody RetailerProfile retailerProfile){

        String response = retailerService.editRetailer(retailerProfile);

        return  ResponseEntity.ok(response);

    }

    @PatchMapping("/deactivate-retailer")
    public ResponseEntity<String> deactivateRetailer(@RequestParam String retailerId){

        return  ResponseEntity.ok(retailerService.deactivateRetailer(retailerId));
    }
}
