package com.bhoj.springbootapp.repository;

import com.bhoj.springbootapp.beans.UnpackagedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnpackagedProductRepository extends JpaRepository<UnpackagedProduct, String> {
}
