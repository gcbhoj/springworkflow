package com.bhoj.springbootapp.controllers;

import com.bhoj.springbootapp.DTO.CashierProfile;
import com.bhoj.springbootapp.DTO.CashierRegistrationRequest;
import com.bhoj.springbootapp.DTO.CashierRegistrationResponse;
import com.bhoj.springbootapp.serviceImpl.CashierServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cashier")
public class CashierController {
    private final CashierServiceImpl cashierService;

    @PostMapping("/addCashiers")
    public ResponseEntity<List<CashierRegistrationResponse>> addCashiers(@Valid @RequestBody List<CashierRegistrationRequest> requests){

        List<CashierRegistrationResponse> responses = cashierService.addNewCashiers(requests);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/all-cashiers")
    public ResponseEntity<List<CashierProfile>> getAllCashiers(@RequestParam String retailerId){
        List<CashierProfile> cashiers = cashierService.getAllCashiers(retailerId);


        return ResponseEntity.ok(cashiers);
    }

    @PatchMapping("/deactivate-cashier")
    public ResponseEntity<String> deActivateCashier(@RequestParam String cashierId){

        return  ResponseEntity.ok(cashierService.removeCashier(cashierId));
    }
}
