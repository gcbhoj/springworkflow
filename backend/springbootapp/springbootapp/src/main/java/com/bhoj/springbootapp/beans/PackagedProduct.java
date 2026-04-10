package com.bhoj.springbootapp.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "packaged_products")
public class PackagedProduct extends Product{

    @Column(nullable = false)
    private String upc;

    @Column(nullable = false)
    private String packageWeight;

    @ElementCollection
    @CollectionTable(
            name = "product_ingredients",
            joinColumns = @JoinColumn(
                    name = "product_id"))
    private List<Ingredients> ingredients;

    private LocalDateTime manufacturedDate;

    private LocalDateTime expiryDate;
    private String manufacturer;
    private String manufacturedIn;
    private Long quantity;


}
