package com.cibertec.netTech.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rol;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters and Setters
}