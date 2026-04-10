package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.CashierProfile;
import com.bhoj.springbootapp.DTO.CashierRegistrationRequest;
import com.bhoj.springbootapp.DTO.CashierRegistrationResponse;
import com.bhoj.springbootapp.beans.Cashier;
import com.bhoj.springbootapp.enums.CashierStatus;
import com.bhoj.springbootapp.exceptionHandler.UserCreationException;
import com.bhoj.springbootapp.repository.CashierRepository;
import com.bhoj.springbootapp.services.CashierServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CashierServiceImpl implements CashierServices {
    private  final CashierRepository cashierRepo;
    private final RetailerServiceImpl retailerService;
    @Override
    public List<CashierRegistrationResponse> addNewCashiers(List<CashierRegistrationRequest> requests) {

        if (requests == null || requests.isEmpty()) {
            throw new UserCreationException("REQUEST LIST CANNOT BE EMPTY");
        }

        List<Cashier> cashiers = requests.stream()
                .map(request -> Cashier.builder()
                        .employeeId(request.getEmployeeId())
                        .employeeName(request.getEmployeeName())
                        .status(CashierStatus.ACTIVE)
                        .retailer(retailerService.verifyRetailerStatus(request.getRetailerId()))
                        .build()
                )
                .toList();

        List<Cashier> savedCashiers = cashierRepo.saveAll(cashiers);

        return savedCashiers.stream()
                .map(cashier -> CashierRegistrationResponse.builder()
                        .cashierId(cashier.getCashierId())
                        .message("CASHIER REGISTERED SUCCESSFULLY")
                        .build()
                )
                .toList();
    }

    @Override
    public List<CashierProfile> getAllCashiers(String retailerId) {

        if(retailerId == null || retailerId.isEmpty()){
            throw  new UserCreationException("RETAILER ID IS REQUIRED");
        }

        List<Cashier> cashiers = cashierRepo.getAllCashiersByRetailerRetailerId(retailerId);

        if(cashiers.isEmpty()){
            throw new UserCreationException("NO CASHIERS FOUND BY GIVEN RETAILER ID");
        }

        return cashiers.stream()
                .map(cas->CashierProfile.builder()
                        .cashierId(cas.getCashierId())
                        .cashierName(cas.getEmployeeName())
                        .status(cas.getStatus())
                        .build())

                .toList();
    }

    @Override
    public String removeCashier(String cashierId) {
        if(cashierId== null|| cashierId.isBlank()){
            throw new UserCreationException("CASHIER ID IS REQUIRED");
        }

        Cashier cashier = cashierRepo.findById(cashierId)
                .orElseThrow(()-> new UserCreationException("NO CASHIER FOUND BY GIVEN ID"));

        cashier.setStatus(CashierStatus.IN_ACTIVE);

        cashierRepo.save(cashier);

        return "CASHIER STATUS SET TO INACTIVE";
    }
}
