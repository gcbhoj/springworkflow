package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.DTO.RetailerProfile;
import com.bhoj.springbootapp.DTO.RetailerRegistrationRequest;
import com.bhoj.springbootapp.DTO.RetailerRegistrationResponse;

import java.util.List;

public interface RetailerService {

    List<RetailerProfile> getAllRetailers();
    RetailerProfile getRetailerById(String retailerId);
    RetailerRegistrationResponse addNewRetailer(RetailerRegistrationRequest request);
    String editRetailer(RetailerProfile profile);
    String deactivateRetailer(String retailerId);
    String reActivateRetailer(String retailerId);

}
