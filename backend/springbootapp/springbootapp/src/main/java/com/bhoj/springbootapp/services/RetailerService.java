package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.DTO.RegisterRetailerRequest;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.repository.RetailerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetailerService {

    private final RetailerRepository retailerRepo;

    public Retailer saveRetailer(RegisterRetailerRequest request){

        Retailer retailer = Retailer.builder()
                .retailerName(request.getRetailerName())
                .retailerWebURL(request.getRetailerURL())
                .build();

        return retailerRepo.save(retailer);
    }

    public Optional<Retailer> getRetailerById(String id){
        return  retailerRepo.findById(id);
    }

    public Optional<Retailer> fetchRetailerByName(String retailerName){
        return retailerRepo.findByRetailerName(retailerName);
    }

    public List<Retailer> getAllRetailers(){
        return  retailerRepo.findAll();
    }




}
