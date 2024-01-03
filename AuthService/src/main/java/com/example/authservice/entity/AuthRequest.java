package com.example.authservice.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

}