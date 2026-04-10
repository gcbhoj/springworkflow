package com.bhoj.springbootapp.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "unpackaged_products")
public class UnpackagedProduct extends Product{
    private String produceOf;
    private double weight;
    private LocalDateTime packagedDate;



}
