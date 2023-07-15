package com.example.demot.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "custom_user")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
}
