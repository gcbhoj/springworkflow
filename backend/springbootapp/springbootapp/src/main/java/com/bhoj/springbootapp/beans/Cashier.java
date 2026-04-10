package com.bhoj.springbootapp.beans;

import com.bhoj.springbootapp.enums.CashierStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cashiers")
public class Cashier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cashierId;

    private String employeeId;

    private String employeeName;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CashierStatus status = CashierStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;
}
