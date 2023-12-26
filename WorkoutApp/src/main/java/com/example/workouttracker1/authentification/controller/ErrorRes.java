package com.example.workouttracker1.authentification.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@RequiredArgsConstructor
public class ErrorRes {
        HttpStatus httpStatus;
        String message;
}
