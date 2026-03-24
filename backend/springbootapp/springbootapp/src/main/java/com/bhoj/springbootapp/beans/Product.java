package com.bhoj.springbootapp.beans;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.PropertyRef;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    @Column(nullable = false)
    private String itemNumber;
    @Column(nullable = false)
    private String productName;
    private String imageURL;
    @Column(nullable = false)
    private double price;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;


}
