package com.example.workouttracker1.authentification.repository;

import java.util.Optional;

import com.example.workouttracker1.authentification.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
