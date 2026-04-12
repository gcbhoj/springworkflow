package com.bhoj.springbootapp.beans;

import com.bhoj.springbootapp.enums.RetailerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "retailer")
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String retailerId;


    @Column(nullable = false)
    private String retailerName;
    @Column(nullable = false)
    private String retailerWebURL;

    @Enumerated (EnumType.STRING)
    @Builder.Default
    private RetailerStatus status = RetailerStatus.ACTIVE;

    private LocalDateTime activationDate;

    private LocalDateTime lastUpdated;
    private LocalDateTime deactivationDate;


    @Version
    @Builder.Default
    private Long version = 0L;

    @OneToMany(
            mappedBy = "retailer",
            cascade = CascadeType.ALL)
    private List<Cashier> cashiers;

    @PrePersist
    protected void onCreate() {
        activationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        lastUpdated = LocalDateTime.now();
    }

}
