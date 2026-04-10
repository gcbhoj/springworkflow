package com.bhoj.springbootapp.beans;

import com.bhoj.springbootapp.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;


    private LocalDateTime activationDate;

    private LocalDateTime lastUpdated;

    @Version
    @Builder.Default
    private Long version = 0L;

    @PrePersist
    protected void onCreate() {
        activationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        lastUpdated = LocalDateTime.now();
    }


}
