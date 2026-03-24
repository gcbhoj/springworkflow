package com.bhoj.springbootapp.repository;

import com.bhoj.springbootapp.beans.PackagedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagedProductRepository extends JpaRepository<PackagedProduct, String> {
}
