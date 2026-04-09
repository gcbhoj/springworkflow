package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.RetailerProfile;
import com.bhoj.springbootapp.DTO.RetailerRegistrationRequest;
import com.bhoj.springbootapp.DTO.RetailerRegistrationResponse;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.enums.RetailerStatus;
import com.bhoj.springbootapp.exceptionHandler.UserCreationException;
import com.bhoj.springbootapp.repository.RetailerRepository;
import com.bhoj.springbootapp.services.RetailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetailerServiceImpl implements RetailerService {

    private final RetailerRepository retailerRepo;


    @Override
    public List<RetailerProfile> getAllRetailers() {

        List<Retailer> retailers = retailerRepo.findByStatus(RetailerStatus.ACTIVE);

        if(retailers.isEmpty()){
            throw new UserCreationException("NO RETAILERS CURRENTLY REGISTERED");
        }

        return retailers
                .stream()
                .map(
                        r-> RetailerProfile.builder()
                                .retailerId(r.getRetailerId())
                                .retailerName(r.getRetailerName())
                                .retailerWebURL(r.getRetailerWebURL())
                                .status(r.getStatus())
                                .build())
        .toList();

    }

    @Override
    public RetailerProfile getRetailerById(String retailerId) {
        if(retailerId == null){
            throw  new UserCreationException("RETAILER ID IS REQUIRED");
        }

        Retailer retailer = verifyRetailerStatus(retailerId);


        return RetailerProfile.builder()
                .retailerId(retailer.getRetailerId())
                .retailerName(retailer.getRetailerName())
                .retailerWebURL(retailer.getRetailerWebURL())
                .status(retailer.getStatus())
                .build();


    }

    @Override
    public RetailerRegistrationResponse addNewRetailer(RetailerRegistrationRequest request) {

        if (request.getRetailerName() == null || request.getRetailerName().isBlank()) {
            throw new UserCreationException("RETAILER NAME IS REQUIRED");
        }

        if(request.getRetailerURL() == null || request.getRetailerURL().isBlank()){
            throw new UserCreationException("RETAILER WEB URL IS REQUIRED");
        }

        Retailer retailer = Retailer.builder()
                .retailerName(request.getRetailerName())
                .retailerWebURL(request.getRetailerURL())
                .build();

        Retailer savedRetailer = retailerRepo.save(retailer);

        return RetailerRegistrationResponse.builder()
                .retailerId(savedRetailer.getRetailerId())
                .message("RETAILER REGISTERED SUCCESSFULLY")
                .build();
    }

    @Override
    public String editRetailer(RetailerProfile profile) {
        String retailerId = profile.getRetailerId();

        Retailer retailer = verifyRetailerStatus(retailerId);

        retailer.setRetailerName(profile.getRetailerName());
        retailer.setRetailerWebURL(profile.getRetailerWebURL());

        retailerRepo.save(retailer);

        return "RETAILER INFORMATION EDITED SUCCESSFULLY";
    }

    @Override
    public String deactivateRetailer(String retailerId) {

        if(retailerId == null || retailerId.isBlank()){
            throw new UserCreationException("RETAILER ID CANNOT BE BLANK");
        }

        Retailer retailer = verifyRetailerStatus(retailerId);

        retailer.setStatus(RetailerStatus.DEACTIVATED);

        retailerRepo.save(retailer);

        return "RETAILER DEACTIVATED SUCCESSFULLY";

    }

    @Override
    public String reActivateRetailer(String retailerId) {
        if(retailerId == null || retailerId.isBlank()){
            throw  new UserCreationException("RETAILER ID CANNOT BE BLANK");
        }
        Retailer retailer = findRetailerById(retailerId);

        if(retailer.getStatus() == (RetailerStatus.DEACTIVATED)){
            retailer.setStatus(RetailerStatus.ACTIVE);

            retailerRepo.save(retailer);

            return "RETAILER STATUS CHANGED TO ACTIVE";
        }

        return "RETAILER IS ALREADY ACTIVE";
    }

    public Retailer getRetailerByName(String retailerName){

        if(retailerName == null){
            throw new UserCreationException("RETAILER NAME IS REQUIRED");
        }

        Retailer retailer = retailerRepo.findByRetailerName(retailerName);

        if(retailer == null){
            throw new UserCreationException("NO RETAILER FOUND BY GIVEN NAME");
        }

        return  retailer;
    }


    public Retailer verifyRetailerStatus(String retailerId){
        Retailer retailer =  retailerRepo.findById(
                retailerId).orElseThrow(
                () -> new UserCreationException("NO RETAILER FOUND BY GIVEN ID"));

        if(retailer.getStatus().equals(RetailerStatus.DEACTIVATED)){
            throw new UserCreationException("RETAILER IS DEACTIVATED");
        }

        return retailer;

    }

    public Retailer findRetailerById(String retailerId){

        return retailerRepo.findById(retailerId)
                .orElseThrow(() -> new UserCreationException("NO RETAILER FOUND BY GIVEN ID"));
    }
}
