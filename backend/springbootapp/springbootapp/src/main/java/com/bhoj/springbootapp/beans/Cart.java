package com.bhoj.springbootapp.beans;

import com.bhoj.springbootapp.enums.CartStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.Format;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Enumerated (EnumType.STRING)
    @Builder.Default
    private CartStatus status = CartStatus.CREATED;

    @Builder.Default
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal budget = BigDecimal.ZERO;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();

    private LocalDateTime transactionStart;
    private LocalDateTime lastUpdated;


    private LocalDateTime transactionEnd;


    @PrePersist
    protected void onCreate() {
        transactionStart = LocalDateTime.now();
    }
    @PreUpdate
    protected  void onUpdate(){
        this.lastUpdated = LocalDateTime.now();

    }

    public  void addCartItem(CartItem item){
        this.cartItems.add(item);
        item.setCart(this);
    }

}