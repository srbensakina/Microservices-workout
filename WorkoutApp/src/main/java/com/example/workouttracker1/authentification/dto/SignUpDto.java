package com.example.workouttracker1.authentification.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
