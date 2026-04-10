package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.DTO.CashierProfile;
import com.bhoj.springbootapp.DTO.CashierRegistrationRequest;
import com.bhoj.springbootapp.DTO.CashierRegistrationResponse;

import java.util.List;

public interface CashierServices {

    List<CashierRegistrationResponse> addNewCashiers(List<CashierRegistrationRequest> requests);
    List<CashierProfile> getAllCashiers(String retailerId);
    String removeCashier(String cashierId);


}
