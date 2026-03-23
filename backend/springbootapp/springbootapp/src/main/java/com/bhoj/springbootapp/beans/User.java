package com.bhoj.springbootapp.beans;

import jakarta.persistence.*;
import lombok.*;

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
    private String email;
}
