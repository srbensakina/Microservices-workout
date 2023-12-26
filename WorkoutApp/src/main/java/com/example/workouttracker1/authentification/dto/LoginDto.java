package com.example.workouttracker1.authentification.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDto {
    private String username;
    private String password;

}
