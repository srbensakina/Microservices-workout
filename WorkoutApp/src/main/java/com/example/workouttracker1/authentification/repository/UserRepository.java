package com.example.workouttracker1.authentification.repository;

import com.example.workouttracker1.authentification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Integer> {
   // User findByUserNameOrEmail(String username, String email);

    User findByEmail(String email);



}
