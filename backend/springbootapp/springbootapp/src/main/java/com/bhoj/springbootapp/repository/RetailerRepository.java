package com.bhoj.springbootapp.repository;

import com.bhoj.springbootapp.beans.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, String> {

    Optional<Retailer> findByRetailerName(String retailerName);}
