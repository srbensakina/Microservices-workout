package com.example.workouttracker1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WorkoutTracker1Application {
    public static void main(String[] args) {
        SpringApplication.run(WorkoutTracker1Application.class, args);
    }
}
