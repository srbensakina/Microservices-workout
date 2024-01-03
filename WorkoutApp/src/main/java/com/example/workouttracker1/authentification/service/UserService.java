package com.example.workouttracker1.authentification.service;

import com.example.workouttracker1.authentification.entity.User;
import com.example.workouttracker1.authentification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

        public User save(User user) {
            return this.repository.save(user);
        }

        public User getById(Integer id) {
            return this.repository.findById(id).orElse(null);
        }

    }
