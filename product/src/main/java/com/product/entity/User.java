package com.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // Avoid reserved keyword "user" in DB
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // Expected values: "ROLE_USER" or "ROLE_ADMIN"
}
